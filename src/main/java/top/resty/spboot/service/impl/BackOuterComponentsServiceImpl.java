package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.BackOuterComponents;
import top.resty.spboot.mapper.BackOuterComponentsMapper;
import top.resty.spboot.service.BackOuterComponentsService;

import java.util.Map;

@Service
public class BackOuterComponentsServiceImpl extends ServiceImpl<BackOuterComponentsMapper, BackOuterComponents> implements BackOuterComponentsService {

    @Autowired
    private BackOuterComponentsMapper mapper;

    public BackOuterComponents getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
