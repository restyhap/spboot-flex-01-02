package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Arms;

public interface ArmsService extends IService<Arms> {
    Arms getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
