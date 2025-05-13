package top.resty.project.dto;

import lombok.Data;
import top.resty.project.entity.DefectImages;
import top.resty.project.entity.Defects;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月16 - 17:07
 */
@Data
public class DefectsDTO {
    private Defects defects;
    private List<DefectImages> defectImages;
}
