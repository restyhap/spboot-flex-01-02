package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Bases;
import top.resty.spboot.mapper.BasesMapper;
import top.resty.spboot.service.BasesService;
import org.springframework.stereotype.Service;

/**
 * 底座信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class BasesServiceImpl extends ServiceImpl<BasesMapper, Bases> implements BasesService {

}
