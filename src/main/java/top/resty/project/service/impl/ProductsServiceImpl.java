package top.resty.project.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Products;
import top.resty.project.mapper.ProductsMapper;
import top.resty.project.service.ProductsService;

@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

}
