package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Arms;
import top.resty.spboot.mapper.ArmsMapper;
import top.resty.spboot.service.ArmsService;
import org.springframework.stereotype.Service;

/**
 * 扶手信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class ArmsServiceImpl extends ServiceImpl<ArmsMapper, Arms> implements ArmsService {

}
