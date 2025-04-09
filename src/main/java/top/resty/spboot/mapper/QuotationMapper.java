package top.resty.spboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.resty.spboot.entity.Quotation;

/**
 * 报价单 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface QuotationMapper extends BaseMapper<Quotation> {

}
