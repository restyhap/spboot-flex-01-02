package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.User;
import top.resty.spboot.mapper.UserMapper;
import top.resty.spboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
