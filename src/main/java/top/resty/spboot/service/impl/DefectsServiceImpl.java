package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Defects;
import top.resty.spboot.mapper.DefectsMapper;
import top.resty.spboot.service.DefectsService;
import org.springframework.stereotype.Service;

/**
 * 缺陷记录表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class DefectsServiceImpl extends ServiceImpl<DefectsMapper, Defects> implements DefectsService {

}
