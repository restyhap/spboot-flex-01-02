package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.Fittings;

public interface FittingsService extends IService<Fittings> {
    Fittings getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
