package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Upholstery;

/**
 * 面料信息表 服务层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
public interface UpholsteryService extends IService<Upholstery> {
  Upholstery getByProductId(Long productId);

  boolean removeByProductId(Long productId);
}
