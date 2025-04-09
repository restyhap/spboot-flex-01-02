package top.resty.spboot.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.resty.spboot.entity.Products;

/**
 * 产品基本信息表 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {

}
