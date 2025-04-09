package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.ProductImages;
import top.resty.spboot.mapper.ProductImagesMapper;
import top.resty.spboot.service.ProductImagesService;

import java.util.Map;

@Service
public class ProductImagesServiceImpl extends ServiceImpl<ProductImagesMapper, ProductImages> implements ProductImagesService {
    @Autowired
    private ProductImagesMapper mapper;

    @Override
    public ProductImages getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("prod_id", productId)); // 修改字段名
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("prod_id", productId)) > 0;
    }
}
