package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.ProductDimensions;
import top.resty.project.mapper.ProductDimensionsMapper;
import top.resty.project.service.ProductDimensionsService;

import java.util.Map;

@Service
public class ProductDimensionsServiceImpl extends ServiceImpl<ProductDimensionsMapper, ProductDimensions> implements ProductDimensionsService {
    @Autowired
    private ProductDimensionsMapper mapper;

    public ProductDimensions getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
