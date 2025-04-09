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
 * 气压罩信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_gas_lift_cover")
@SearchBean(tables = "tb_gas_lift_cover")
public class GasLiftCover implements Serializable {

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
     * 材料
     */
    private String material;

    /**
     * 颜色
     */
    private String colour;

}
