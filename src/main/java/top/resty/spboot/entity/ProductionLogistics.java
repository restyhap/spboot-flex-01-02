package top.resty.spboot.entity;

import com.mybatisflex.annotation.Column;
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
 * 生产和物流信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_production_logistics")
@SearchBean(tables = "tb_production_logistics")
public class ProductionLogistics implements Serializable {

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
     * 生产时间(天)
     */
    private Integer productionTime;

    /**
     * 有效体积(m³)
     */
    private BigDecimal effectiveVolume;

    /**
     * 20尺标准柜装载数量
     */
    @Column("loading_quantity_20gp")
    private Integer loadingQuantity20gp;

    /**
     * 40尺高柜装载数量
     */
    @Column("loading_quantity_40hc")
    private Integer loadingQuantity40hc;

    /**
     * 净重(kg)
     */
    private BigDecimal netWeight;

    /**
     * 毛重(kg)
     */
    private BigDecimal grossWeight;

}
