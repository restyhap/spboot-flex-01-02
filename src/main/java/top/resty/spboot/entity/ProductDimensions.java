package top.resty.spboot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import cn.zhxu.bs.bean.SearchBean;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品尺寸表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_product_dimensions")
@SearchBean(tables = "tb_product_dimensions")
public class ProductDimensions implements Serializable {

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
     * 座位宽度(mm)
     */
    private Integer seatWidth;

    /**
     * 座位深度(mm)
     */
    private Integer seatDepth;

    /**
     * 座位最小高度(mm)
     */
    private Integer seatHeightMin;

    /**
     * 座位最大高度(mm)
     */
    private Integer seatHeightMax;

    /**
     * 靠背宽度(mm)
     */
    private Integer backWidth;

    /**
     * 靠背高度(mm)
     */
    private Integer backHeight;

    /**
     * 靠背高度从座位(mm)
     */
    private Integer backHeightFromSeat;

    /**
     * 整体宽度(mm)
     */
    private Integer overallWidth;

    /**
     * 整体深度(mm)
     */
    private Integer overallDepth;

    /**
     * 整体最小高度(mm)
     */
    private Integer overallHeightMin;

    /**
     * 整体最大高度(mm)
     */
    private Integer overallHeightMax;

}
