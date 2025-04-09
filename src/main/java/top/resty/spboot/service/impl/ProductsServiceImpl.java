package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Products;
import top.resty.spboot.mapper.ProductsMapper;
import top.resty.spboot.service.ProductsService;
import org.springframework.stereotype.Service;

/**
 * 产品基本信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

}
