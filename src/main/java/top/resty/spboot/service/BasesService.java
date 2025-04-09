package top.resty.spboot.service;

import com.mybatisflex.core.service.IService;
import top.resty.spboot.entity.Bases;

public interface BasesService extends IService<Bases> {
    Bases getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
