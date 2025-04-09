package top.resty.spboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.resty.spboot.entity.QcReports;

/**
 * 质检报告基本信息表 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface QcReportsMapper extends BaseMapper<QcReports> {

}
