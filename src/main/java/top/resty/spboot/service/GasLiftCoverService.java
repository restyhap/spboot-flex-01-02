package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.GasLiftCover;

public interface GasLiftCoverService extends IService<GasLiftCover> {
    GasLiftCover getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
