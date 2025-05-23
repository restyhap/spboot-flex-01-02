package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.ProductionLogistics;
import top.resty.spboot.mapper.ProductionLogisticsMapper;
import top.resty.spboot.service.ProductionLogisticsService;

import java.util.Map;

@Service
public class ProductionLogisticsServiceImpl extends ServiceImpl<ProductionLogisticsMapper, ProductionLogistics> implements ProductionLogisticsService {
    @Autowired
    private ProductionLogisticsMapper mapper;
    @Override
    public ProductionLogistics getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
