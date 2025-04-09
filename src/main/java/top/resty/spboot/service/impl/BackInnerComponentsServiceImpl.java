package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.BackInnerComponents;
import top.resty.spboot.mapper.BackInnerComponentsMapper;
import top.resty.spboot.service.BackInnerComponentsService;

import java.util.Map;

@Service
public class BackInnerComponentsServiceImpl extends ServiceImpl<BackInnerComponentsMapper, BackInnerComponents> implements BackInnerComponentsService {
    @Autowired
    private BackInnerComponentsMapper mapper;

    public BackInnerComponents getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
