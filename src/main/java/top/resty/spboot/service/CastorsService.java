package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Castors;

public interface CastorsService extends IService<Castors> {
    Castors getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
