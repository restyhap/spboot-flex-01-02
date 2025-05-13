package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.Arms;

public interface ArmsService extends IService<Arms> {
    Arms getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
