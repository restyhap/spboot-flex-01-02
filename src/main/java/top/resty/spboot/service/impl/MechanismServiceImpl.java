package top.resty.spboot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.Mechanism;
import top.resty.spboot.mapper.MechanismMapper;
import top.resty.spboot.service.MechanismService;

import java.util.Map;

@Service
public class MechanismServiceImpl extends ServiceImpl<MechanismMapper, Mechanism> implements MechanismService {

    @Autowired
    private MechanismMapper mapper;

    public Mechanism getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
