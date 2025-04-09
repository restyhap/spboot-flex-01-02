package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.SeatInnerComponents;
import top.resty.spboot.mapper.SeatInnerComponentsMapper;
import top.resty.spboot.service.SeatInnerComponentsService;

import java.util.Map;

@Service
public class SeatInnerComponentsServiceImpl extends ServiceImpl<SeatInnerComponentsMapper, SeatInnerComponents> implements SeatInnerComponentsService {
    @Autowired
    private SeatInnerComponentsMapper mapper;
    @Override
    public SeatInnerComponents getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
