package top.resty.project.service;

import top.resty.project.dto.QcReportsDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月16 - 17:13
 */
public interface QcReportsDTOService {

  boolean save(QcReportsDTO qcReportsDTO);

  int delete(String id);

  QcReportsDTO get(String id);

}
