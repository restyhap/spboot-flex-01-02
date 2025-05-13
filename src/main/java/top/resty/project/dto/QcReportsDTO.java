package top.resty.project.dto;

import lombok.Data;
import top.resty.project.entity.QcReports;

import java.util.List;

@Data
public class QcReportsDTO {
    private QcReports qcReports;
    private List<DefectsDTO> defectsDTO;
}
