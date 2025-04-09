package top.resty.spboot.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import cn.zhxu.bs.bean.SearchBean;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 质检报告基本信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_qc_reports")
@SearchBean(tables = "tb_qc_reports")
public class QcReports implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @Id
    private String id;

    /**
     * 型号代码
     */
    private String modelCode;

    /**
     * 工厂代码
     */
    private String factoryCode;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 客户
     */
    private String client;

    /**
     * PO编号
     */
    private String poNumber;

    /**
     * 检验日期
     */
    private Date inspectionDate;

    /**
     * 订单数量
     */
    private Integer orderQty;

    /**
     * 报告日期
     */
    private Date reportDate;

    /**
     * 检验数量
     */
    private Integer inspectQty;

    /**
     * 质检员
     */
    private String qcOfficer;

    /**
     * 通过/失败
     */
    private String passFail;

    /**
     * 二次质检日期
     */
    private Date secondQcDate;

    /**
     * 评价内容
     */
    private String comments;

    /**
     * 仓库库存图片
     */
    private String stocksInWarehouse;

    /**
     * 产品抽样数量图片
     */
    private String samplingOfProductsQuantity;

    /**
     * 运输标记图片
     */
    private String shippingMarks;

    /**
     * 条形码图片
     */
    private String barcode;

    /**
     * 外包装图片
     */
    private String packingOutside;

    /**
     * 内包装图片
     */
    private String packingInside;

    /**
     * 椅子组件-已包装图片
     */
    private String chairComponentsPacked;

    /**
     * 椅子组件-未包装图片
     */
    private String chairComponentsUnpacked;

    /**
     * 配件包-已包装图片
     */
    private String fittingPackPacked;

    /**
     * 配件包-未包装图片
     */
    private String fittingPackUnpacked;

    /**
     * 生产标签图片
     */
    private String productionLabel;

    /**
     * 组装说明图片
     */
    private String assemblyInstructions;

    /**
     * 组件图片-座椅
     */
    private String imageOfComponentsSeat;

    /**
     * 组件图片-靠背
     */
    private String imageOfComponentsBack;

    /**
     * 组件图片-底座
     */
    private String imageOfComponentsBase;

    /**
     * 组件图片-脚轮
     */
    private String imageOfComponentsCastors;

    /**
     * 组件图片-气压棒外罩
     */
    private String imageOfComponentsGasLiftCover;

    /**
     * 组件图片-气压棒标记
     */
    private String imageOfComponentsGasLiftStamp;

    /**
     * 组件图片-扶手
     */
    private String imageOfComponentsArmrest;

    /**
     * 组件图片-机构
     */
    private String imageOfComponentMechanism;

    /**
     * 组件图片-头枕
     */
    private String imageOfComponentsHeadrest;

    /**
     * 成品图片-正视图
     */
    private String imageOfProductBuiltFront;

    /**
     * 成品图片-侧视图
     */
    private String imageOfProductBuiltSide;

    /**
     * 成品图片-背视图
     */
    private String imageOfProductBuiltBack;

    /**
     * 成品图片-45度视图
     */
    @Column("image_of_product_built_45_degree")
    private String imageOfProductBuilt45Degree;

    /**
     * 成品图片-样品对比图1
     */
    @Column("front_image_of_product_built_compare_1")
    private String frontImageOfProductBuiltCompare1;

    /**
     * 成品图片-样品对比图2
     */
    @Column("front_image_of_product_built_compare_2")
    private String frontImageOfProductBuiltCompare2;

    /**
     * 功能检查-座椅高度调节
     */
    private String functionCheckSeatHeightExtension;

    /**
     * 功能检查-机构调节
     */
    private String functionCheckMechanismAdjustment;

    /**
     * 功能检查-扶手调节
     */
    private String functionCheckArmrestAdjustment;

    /**
     * 功能检查-头枕调节
     */
    private String functionCheckHeadrestAdjustment;

    /**
     * 功能检查-其他1
     */
    private String functionCheckOther1;

    /**
     * 功能检查-其他2
     */
    private String functionCheckOther2;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

}
