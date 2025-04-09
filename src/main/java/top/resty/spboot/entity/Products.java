/*
 * @Author: resty restyhap@hotmail.com
 * @Date: 2025-02-25 18:29:39
 * @LastEditors: resty restyhap@hotmail.com
 * @LastEditTime: 2025-04-04 20:31:06
 * @FilePath: /yarn-vite-web-01-02/Users/resty-mac/02-workspace/99_project/2025.01/spboot-flex-01-02/src/main/java/top/resty/spboot/entity/Products.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package top.resty.spboot.entity;


import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

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
    @DbField("fob_20_container_price")
    private BigDecimal fob20ContainerPrice;

    /**
     * 40尺柜FOB价格
     */
    @Column("fob_40_container_price")
    @DbField("fob_40_container_price")
    private BigDecimal fob40ContainerPrice;

    /**
     * 发货港口
     */
    private String shippingPort;

}
