package top.resty.project.entity;

import cn.zhxu.bs.bean.SearchBean;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 泡棉信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_foam_details")
@SearchBean(tables = "tb_foam_details")
public class FoamDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @Id
    private String id;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 描述
     */
    private String description;

    /**
     * 座椅密度(kg/m³)
     */
    private Integer seatDensity;

    /**
     * 靠背密度(kg/m³)
     */
    private Integer backDensity;

    /**
     * 座椅厚度(mm)
     */
    private Integer seatThickness;

    /**
     * 靠背厚度(mm)
     */
    private Integer backThickness;

}
