package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.resty.spboot.dto.QcReportsDTO;
import top.resty.spboot.entity.QcReports;
import top.resty.spboot.mapper.QcReportsMapper;
import top.resty.spboot.service.QcReportsDTOService;
import top.resty.spboot.service.QcReportsService;

/**
 * 质检报告基本信息表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class QcReportsServiceImpl extends ServiceImpl<QcReportsMapper, QcReports> implements QcReportsService {

    @Autowired
    private QcReportsDTOService qcReportsDTOService;

    @Override
    public QcReportsDTO getQcReportDTOById(String id) {
        return qcReportsDTOService.get(id);
    }
}
