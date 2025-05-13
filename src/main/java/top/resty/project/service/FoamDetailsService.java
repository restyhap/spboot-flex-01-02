package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.FoamDetails;

public interface FoamDetailsService extends IService<FoamDetails> {
    FoamDetails getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
