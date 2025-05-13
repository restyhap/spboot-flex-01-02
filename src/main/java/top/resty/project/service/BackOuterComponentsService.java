package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.BackOuterComponents;

public interface BackOuterComponentsService extends IService<BackOuterComponents> {
    BackOuterComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
