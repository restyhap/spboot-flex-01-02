package top.resty.spboot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import cn.zhxu.bs.bean.SearchBean;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 面料信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_upholstery")
@SearchBean(tables = "tb_upholstery")
public class Upholstery implements Serializable {

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
     * 面料制造商
     */
    private String fabricManufacturer;

    /**
     * 颜色代码
     */
    private String colourCode;

    /**
     * 皮革等级
     */
    private String leatherGrade;

    /**
     * 每把椅子用料(m²)
     */
    private BigDecimal usagePerChair;

}
