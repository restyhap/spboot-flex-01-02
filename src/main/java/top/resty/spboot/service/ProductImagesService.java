package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.ProductImages;

public interface ProductImagesService extends IService<ProductImages> {
    ProductImages getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
