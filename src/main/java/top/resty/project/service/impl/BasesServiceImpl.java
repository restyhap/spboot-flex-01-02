package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Bases;
import top.resty.project.mapper.BasesMapper;
import top.resty.project.service.BasesService;

import java.util.Map;

@Service
public class BasesServiceImpl extends ServiceImpl<BasesMapper, Bases> implements BasesService {
    @Autowired
    private BasesMapper mapper;

    public Bases getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
