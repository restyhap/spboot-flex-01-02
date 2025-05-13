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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 报价单 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_quotation")
@SearchBean(tables = "tb_quotation")
public class Quotation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @Id
    private String id;

    /**
     * 图片
     */
    private String image;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 供应商项目代码
     */
    private String supplierItemCode;

    /**
     * 规格详细信息
     */
    private String specificationDetails;

    /**
     * 样品交付周期
     */
    private LocalDateTime sampleLeadTime;

    /**
     * 总体尺寸宽度
     */
    private Integer overallDimensionsWidth;

    /**
     * 总体尺寸深度
     */
    private Integer overallDimensionsDepth;

    /**
     * 总体尺寸高度
     */
    private Integer overallDimensionsHeight;

    /**
     * 箱体尺寸宽度
     */
    private Integer boxDimensionsWidth;

    /**
     * 箱体尺寸深度
     */
    private Integer boxDimensionsDepth;

    /**
     * 箱体尺寸高度
     */
    private Integer boxDimensionsHeight;

    /**
     * 箱体重量净重
     */
    private Integer boxWeightNetWeighth;

    /**
     * 箱体重量毛重
     */
    private String netWeightGrossWeight;

    /**
     * 有效体积
     */
    private String effectiveVol;

    /**
     * 装载量
     */
    private Integer loadingQty;

    /**
     * 最小订单量
     */
    private String moq;

    /**
     * 供货商成本价
     */
    private BigDecimal fobPrice;

    /**
     * 货币单位
     */
    private Integer currency;

    /**
     * 测试标准
     */
    private Integer bifmaTested;

    /**
     * 3D模块
     */
    private Integer cadBlockAvailable;

    /**
     * 产品数据
     */
    private Integer productDataAvailable;

    /**
     * 产品图片
     */
    private Integer productImagesAvailable;

    /**
     * 销售
     */
    private String salesContacts;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 有效期
     */
    private String validPeriod;

    /**
     * 港口
     */
    private String port;

    /**
     * 备注
     */
    private String remark;

}
