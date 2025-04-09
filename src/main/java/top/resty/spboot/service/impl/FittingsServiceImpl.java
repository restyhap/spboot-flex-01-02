package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Fittings;
import top.resty.spboot.mapper.FittingsMapper;
import top.resty.spboot.service.FittingsService;
import org.springframework.stereotype.Service;

/**
 * 配件信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class FittingsServiceImpl extends ServiceImpl<FittingsMapper, Fittings> implements FittingsService {

}
