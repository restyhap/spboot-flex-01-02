package top.resty.spboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.resty.spboot.entity.User;

/**
 *  映射层。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
