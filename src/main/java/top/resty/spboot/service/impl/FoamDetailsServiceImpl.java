package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.FoamDetails;
import top.resty.spboot.mapper.FoamDetailsMapper;
import top.resty.spboot.service.FoamDetailsService;

import java.util.Map;

@Service
public class FoamDetailsServiceImpl extends ServiceImpl<FoamDetailsMapper, FoamDetails> implements FoamDetailsService {

    @Autowired
    private FoamDetailsMapper mapper;

    public FoamDetails getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
