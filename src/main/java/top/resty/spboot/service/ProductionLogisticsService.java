package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.ProductionLogistics;

public interface ProductionLogisticsService extends IService<ProductionLogistics> {
    ProductionLogistics getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
