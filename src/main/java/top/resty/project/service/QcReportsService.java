package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.dto.QcReportsDTO;
import top.resty.project.entity.QcReports;

/**
 * 质检报告基本信息表 服务层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
public interface QcReportsService extends IService<QcReports> {

    /**
     * Get QcReportsDTO by ID
     *
     * @param id QcReports ID
     * @return QcReportsDTO object
     */
    QcReportsDTO getQcReportDTOById(String id);
}
