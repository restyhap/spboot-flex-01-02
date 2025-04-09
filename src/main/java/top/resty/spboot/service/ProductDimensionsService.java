package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.ProductDimensions;

public interface ProductDimensionsService extends IService<ProductDimensions> {
    ProductDimensions getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
