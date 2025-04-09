package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Fittings;

public interface FittingsService extends IService<Fittings> {
    Fittings getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
