package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.ProductionLogistics;

public interface ProductionLogisticsService extends IService<ProductionLogistics> {
    ProductionLogistics getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
