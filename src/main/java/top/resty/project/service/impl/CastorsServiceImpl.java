package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Castors;
import top.resty.project.mapper.CastorsMapper;
import top.resty.project.service.CastorsService;

import java.util.Map;

@Service
public class CastorsServiceImpl extends ServiceImpl<CastorsMapper, Castors> implements CastorsService {

    @Autowired
    private CastorsMapper mapper;

    public Castors getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
