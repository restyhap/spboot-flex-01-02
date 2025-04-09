package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.CartonDetails;
import top.resty.spboot.mapper.CartonDetailsMapper;
import top.resty.spboot.service.CartonDetailsService;

import java.util.Map;

@Service
public class CartonDetailsServiceImpl extends ServiceImpl<CartonDetailsMapper, CartonDetails> implements CartonDetailsService {

    @Autowired
    private CartonDetailsMapper mapper;

    public CartonDetails getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
