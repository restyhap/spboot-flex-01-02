package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.SeatOuterComponents;

public interface SeatOuterComponentsService extends IService<SeatOuterComponents> {
    SeatOuterComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
