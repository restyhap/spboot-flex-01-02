package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.GasLift;
import top.resty.spboot.mapper.GasLiftMapper;
import top.resty.spboot.service.GasLiftService;

import java.util.Map;

@Service
public class GasLiftServiceImpl extends ServiceImpl<GasLiftMapper, GasLift> implements GasLiftService {

    @Autowired
    private  GasLiftMapper mapper;
    public GasLift getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
