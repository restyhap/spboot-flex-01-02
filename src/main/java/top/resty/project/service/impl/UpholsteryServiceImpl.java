package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Upholstery;
import top.resty.project.mapper.UpholsteryMapper;
import top.resty.project.service.UpholsteryService;

import java.util.Map;

@Service
public class UpholsteryServiceImpl extends ServiceImpl<UpholsteryMapper, Upholstery> implements UpholsteryService {
    @Autowired
    private UpholsteryMapper mapper;
    @Override
    public Upholstery getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId))>0;
    }
}
