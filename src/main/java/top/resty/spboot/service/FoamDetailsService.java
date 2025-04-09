package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.FoamDetails;

public interface FoamDetailsService extends IService<FoamDetails> {
    FoamDetails getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
