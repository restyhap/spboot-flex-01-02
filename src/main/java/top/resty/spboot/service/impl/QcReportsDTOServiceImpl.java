/*
 * @Author: resty restyhap@hotmail.com
 * @Date: 2025-02-16 17:15:11
 * @LastEditors: resty restyhap@hotmail.com
 * @LastEditTime: 2025-02-16 18:43:57
 * @FilePath: /yarn-vite-web-01-02/Users/resty-mac/02-workspace/99_project/2025.01/spboot-flex-01-02/src/main/java/top/resty/spboot/service/impl/QcReportsDTOServiceImpl.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package top.resty.spboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.resty.spboot.dto.DefectsDTO;
import top.resty.spboot.dto.QcReportsDTO;
import top.resty.spboot.entity.DefectImages;
import top.resty.spboot.entity.Defects;
import top.resty.spboot.entity.QcReports;
import top.resty.spboot.mapper.DefectImagesMapper;
import top.resty.spboot.mapper.DefectsMapper;
import top.resty.spboot.mapper.QcReportsMapper;
import top.resty.spboot.service.DefectImagesService;
import top.resty.spboot.service.DefectsService;
import top.resty.spboot.service.QcReportsDTOService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月16 - 17:15
 */
@Service
public class QcReportsDTOServiceImpl implements QcReportsDTOService {

  @Autowired
  private QcReportsMapper qcReportsMapper ;
  @Autowired
  private DefectsMapper defectsMapper ;
  @Autowired
  private DefectImagesMapper defectImagesMapper ;

  @Autowired
  private DefectsService defectsService ;

  @Autowired
  private DefectImagesService defectsImageService ;

  @Override
  @Transactional
  public boolean save(QcReportsDTO qcReportsDTO) {
    try {
      // 业务规则验证
      if (qcReportsDTO.getQcReports() == null) {
        throw new IllegalArgumentException("质检报告不能为空");
      }

      List<DefectsDTO> defectsList = qcReportsDTO.getDefectsDTO();
      if (defectsList != null) {
        for (DefectsDTO defect : defectsList) {
          if (defect.getDefectImages() == null || defect.getDefectImages().isEmpty()) {
            throw new IllegalArgumentException("每个缺陷记录必须至少包含一张图片");
          }
        }
      }

      QcReports qcReports = qcReportsDTO.getQcReports();

      // 保存质检报告
      int qcReportsResult = qcReportsMapper.save(qcReports);
      if (qcReportsResult <= 0) {
        throw new RuntimeException("保存质检报告失败");
      }

      // 如果有缺陷记录，则保存缺陷
      if (!defectsList.isEmpty()) {
        // 修改这里的转换逻辑
        List<Defects> defectsEntityList = defectsList.stream()
          .map(defectDTO -> {
            Defects defect = new Defects();
            // 从 defectDTO.getDefects() 获取数据
            BeanUtil.copyProperties(defectDTO.getDefects(), defect);
            return defect;
          })
          .collect(Collectors.toList());

        int defectsResult = defectsMapper.batchSave(defectsEntityList);
        if (defectsResult <= 0) {
          throw new RuntimeException("保存缺陷记录失败");
        }

        // 收集所有非空的缺陷图片
        List<DefectImages> allDefectImages = new ArrayList<>();
        defectsList.forEach(defects -> {
          if (defects.getDefectImages() != null && !defects.getDefectImages().isEmpty()) {
            allDefectImages.addAll(defects.getDefectImages());
          }
        });

        // 如果有缺陷图片，则保存图片
        if (!allDefectImages.isEmpty()) {
          int defectImagesResult = defectImagesMapper.batchSave(allDefectImages);
          if (defectImagesResult <= 0) {
            throw new RuntimeException("保存缺陷图片失败");
          }
        }
      }

      return true;
    } catch (Exception e) {
      throw new RuntimeException("保存质检报告失败: " + e.getMessage(), e);
    }
  }

  @Override
  @Transactional
  public int delete(String id) {
    // 删除质检报告
    int qcReportsResult = qcReportsMapper.deleteById(id);
    if (qcReportsResult > 0) {
      // 删除关联的缺陷记录
      List<Defects> defectsList = defectsMapper.getInfoByReportId(id);
      if (!defectsList.isEmpty()) {
        List<String> defectIds = defectsList.stream()
          .map(Defects::getId)
          .collect(Collectors.toList());
        defectsMapper.deleteByIds(defectIds);

        // 删除关联的缺陷图片
        defectImagesMapper.deleteByDefectIds(defectIds);
      }
    }
    return 0;
  }



  @Override
  public QcReportsDTO get(String id) {
    QcReportsDTO qcReportsDTO = new QcReportsDTO();
    QcReports qcReports = qcReportsMapper.selectOneById(id);
    if (qcReports != null) {
      // qcReportsDTO 添加 qcReports
      qcReportsDTO.setQcReports(qcReports);

      List<Defects> defectsList = defectsMapper.getInfoByReportId(id);
      if (!defectsList.isEmpty()) {
        // qcReportsDTO 添加 List<DefectsDTO> defectsDTO
        List<DefectsDTO> list = new ArrayList<>();

        for (Defects defects : defectsList) {
          DefectsDTO defectsDTO = new DefectsDTO();
          List<DefectImages> defectImagesList = defectImagesMapper.getInfoByDefectId(defects.getId());
          defectsDTO.setDefects(defects);
          defectsDTO.setDefectImages(defectImagesList);
          list.add(defectsDTO);
        }
        System.out.println(list);
        qcReportsDTO.setDefectsDTO(list);
      }
      return qcReportsDTO;
    }else{
      return null;
    }


  }


}
