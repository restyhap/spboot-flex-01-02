package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.ProductImages;

public interface ProductImagesService extends IService<ProductImages> {
    ProductImages getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
