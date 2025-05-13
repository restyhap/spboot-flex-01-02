package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.DefectImages;

/**
 * 缺陷图片关联表 服务层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
public interface DefectImagesService extends IService<DefectImages> {

  int removeByDefectId(String id);
}
