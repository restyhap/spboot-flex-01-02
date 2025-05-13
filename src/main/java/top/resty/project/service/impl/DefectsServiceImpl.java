package top.resty.project.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Defects;
import top.resty.project.mapper.DefectsMapper;
import top.resty.project.service.DefectsService;

import java.util.List;

/**
 * 缺陷记录表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class DefectsServiceImpl extends ServiceImpl<DefectsMapper, Defects> implements DefectsService {

  @Autowired
  private DefectsMapper defectsMapper;

  @Override
  public List<Defects> getInfoByReportId(String id) {
    return defectsMapper.getInfoByReportId(id);
  }
}
