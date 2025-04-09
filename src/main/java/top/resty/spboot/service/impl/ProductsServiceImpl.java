package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.Products;
import top.resty.spboot.mapper.ProductsMapper;
import top.resty.spboot.service.ProductsService;

@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

}
