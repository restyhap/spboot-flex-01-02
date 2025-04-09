package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Mechanism;

public interface MechanismService extends IService<Mechanism> {
    Mechanism getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
