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
 * 气压棒信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_gas_lift")
@SearchBean(tables = "tb_gas_lift")
public class GasLift implements Serializable {

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
     * 气压等级
     */
    private String gasLiftClass;

    /**
     * 外管长度(mm)
     */
    private Integer casingLength;

    /**
     * 行程(mm)
     */
    private Integer extensionSize;

    /**
     * 锥度(mm)
     */
    private Integer taper;

}
