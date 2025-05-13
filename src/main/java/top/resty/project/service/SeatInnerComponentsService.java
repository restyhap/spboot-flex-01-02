package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.SeatInnerComponents;

public interface SeatInnerComponentsService extends IService<SeatInnerComponents> {
    SeatInnerComponents getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
