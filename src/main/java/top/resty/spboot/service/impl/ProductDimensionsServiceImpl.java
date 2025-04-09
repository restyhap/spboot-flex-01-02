package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.ProductDimensions;
import top.resty.spboot.mapper.ProductDimensionsMapper;
import top.resty.spboot.service.ProductDimensionsService;
import org.springframework.stereotype.Service;

/**
 * 产品尺寸表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class ProductDimensionsServiceImpl extends ServiceImpl<ProductDimensionsMapper, ProductDimensions> implements ProductDimensionsService {

}
