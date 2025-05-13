package top.resty.project.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.project.entity.GasLiftCover;
import top.resty.project.mapper.GasLiftCoverMapper;
import top.resty.project.service.GasLiftCoverService;

import java.util.Map;

@Service
public class GasLiftCoverServiceImpl extends ServiceImpl<GasLiftCoverMapper, GasLiftCover> implements GasLiftCoverService {

    @Autowired
    private GasLiftCoverMapper mapper;

    public GasLiftCover getByProductId(Long productId) {
        return mapper.selectOneByQuery(new QueryWrapper().eq("product_id", productId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        return mapper.deleteByMap(Map.of("product_id", productId)) > 0;
    }
}
