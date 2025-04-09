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
 * 产品基本信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_products")
@SearchBean(tables = "tb_products")
public class Products implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @Id
    private String id;

    /**
     * 产品代码
     */
    private String tccode;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 防火标准
     */
    private String fireStandard;

    /**
     * 20尺柜FOB价格
     */
    @Column("fob_20_container_price")
    private BigDecimal fob20ContainerPrice;

    /**
     * 40尺柜FOB价格
     */
    @Column("fob_40_container_price")
    private BigDecimal fob40ContainerPrice;

    /**
     * 发货港口
     */
    private String shippingPort;

}
