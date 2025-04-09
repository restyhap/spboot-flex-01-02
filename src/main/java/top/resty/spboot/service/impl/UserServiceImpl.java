package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.spboot.entity.User;
import top.resty.spboot.mapper.UserMapper;
import top.resty.spboot.service.UserService;

/**
 *  服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
