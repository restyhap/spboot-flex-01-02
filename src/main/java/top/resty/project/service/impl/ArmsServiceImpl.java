package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Arms;
import top.resty.project.mapper.ArmsMapper;
import top.resty.project.service.ArmsService;

import java.util.Map;

@Service
public class ArmsServiceImpl extends ServiceImpl<ArmsMapper, Arms> implements ArmsService {

    @Autowired
    private ArmsMapper armsMapper;

    public Arms getByProductId(Long productId) {
        return armsMapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
