package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.BackInnerComponents;

public interface BackInnerComponentsService extends IService<BackInnerComponents> {
    BackInnerComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
