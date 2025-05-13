package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.entity.Bases;

public interface BasesService extends IService<Bases> {
    Bases getByProductId(Long productId);

    boolean removeByProductId(Long productId);
}
