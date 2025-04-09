package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.Fittings;
import top.resty.spboot.mapper.FittingsMapper;
import top.resty.spboot.service.FittingsService;

import java.util.Map;

@Service
public class FittingsServiceImpl extends ServiceImpl<FittingsMapper, Fittings> implements FittingsService {


    @Autowired
    private FittingsMapper mapper;

    public Fittings getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
