package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.GasLiftCover;

public interface GasLiftCoverService extends IService<GasLiftCover> {
    GasLiftCover getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
