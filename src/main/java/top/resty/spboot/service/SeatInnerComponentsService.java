package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.SeatInnerComponents;

public interface SeatInnerComponentsService extends IService<SeatInnerComponents> {
    SeatInnerComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
