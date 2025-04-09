package top.resty.spboot.dto;

import lombok.Data;
import top.resty.spboot.entity.QcReports;

import java.util.List;

@Data
public class QcReportsDTO {
    private QcReports qcReports;
    private List<DefectsDTO> defectsDTO;
}
