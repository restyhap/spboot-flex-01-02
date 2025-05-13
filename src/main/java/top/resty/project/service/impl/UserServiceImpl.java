package top.resty.project.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.project.entity.User;
import top.resty.project.mapper.UserMapper;
import top.resty.project.service.UserService;

/**
 *  服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
