package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.SeatOuterComponents;
import top.resty.spboot.mapper.SeatOuterComponentsMapper;
import top.resty.spboot.service.SeatOuterComponentsService;

import java.util.Map;

@Service
public class SeatOuterComponentsServiceImpl extends ServiceImpl<SeatOuterComponentsMapper, SeatOuterComponents> implements SeatOuterComponentsService {
    @Autowired
    private SeatOuterComponentsMapper mapper;

    @Override
    public SeatOuterComponents getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
