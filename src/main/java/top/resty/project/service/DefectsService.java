package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.Defects;

import java.util.List;

/**
 * 缺陷记录表 服务层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
public interface DefectsService extends IService<Defects> {

  List<Defects> getInfoByReportId(String id);
}
