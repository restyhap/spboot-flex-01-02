package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.BackInnerComponents;

public interface BackInnerComponentsService extends IService<BackInnerComponents> {
    BackInnerComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
