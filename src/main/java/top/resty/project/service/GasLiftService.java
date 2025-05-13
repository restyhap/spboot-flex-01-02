package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.GasLift;

public interface GasLiftService extends IService<GasLift> {
    GasLift getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
