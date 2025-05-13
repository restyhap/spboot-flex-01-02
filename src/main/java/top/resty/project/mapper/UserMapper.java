package top.resty.project.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.resty.project.entity.User;

/**
 *  映射层。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
