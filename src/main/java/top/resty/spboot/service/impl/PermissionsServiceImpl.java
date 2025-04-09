package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Permissions;
import top.resty.spboot.mapper.PermissionsMapper;
import top.resty.spboot.service.PermissionsService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}
