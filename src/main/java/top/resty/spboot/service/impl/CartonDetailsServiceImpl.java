package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.CartonDetails;
import top.resty.spboot.mapper.CartonDetailsMapper;
import top.resty.spboot.service.CartonDetailsService;
import org.springframework.stereotype.Service;

/**
 * 包装信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class CartonDetailsServiceImpl extends ServiceImpl<CartonDetailsMapper, CartonDetails> implements CartonDetailsService {

}
