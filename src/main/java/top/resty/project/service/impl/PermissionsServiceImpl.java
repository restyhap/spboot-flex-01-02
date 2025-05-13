package top.resty.project.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Permissions;
import top.resty.project.mapper.PermissionsMapper;
import top.resty.project.service.PermissionsService;

/**
 *  服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}
