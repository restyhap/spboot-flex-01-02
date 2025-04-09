package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.GasLift;

public interface GasLiftService extends IService<GasLift> {
    GasLift getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
