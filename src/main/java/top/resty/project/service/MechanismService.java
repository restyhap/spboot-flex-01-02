package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.Mechanism;

public interface MechanismService extends IService<Mechanism> {
    Mechanism getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
