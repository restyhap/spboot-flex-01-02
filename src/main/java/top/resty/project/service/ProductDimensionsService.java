package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.ProductDimensions;

public interface ProductDimensionsService extends IService<ProductDimensions> {
    ProductDimensions getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
