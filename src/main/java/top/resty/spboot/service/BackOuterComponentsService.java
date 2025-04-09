package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.BackOuterComponents;

public interface BackOuterComponentsService extends IService<BackOuterComponents> {
    BackOuterComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
