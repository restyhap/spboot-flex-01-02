package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.ProductImages;
import top.resty.spboot.mapper.ProductImagesMapper;
import top.resty.spboot.service.ProductImagesService;
import org.springframework.stereotype.Service;

/**
 * 规格图片表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class ProductImagesServiceImpl extends ServiceImpl<ProductImagesMapper, ProductImages> implements ProductImagesService {

}
