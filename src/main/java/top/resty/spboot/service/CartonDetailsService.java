package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.CartonDetails;

public interface CartonDetailsService extends IService<CartonDetails> {
    CartonDetails getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
