package top.resty.spboot.entity;

import cn.zhxu.bs.bean.SearchBean;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 扶手信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_arms")
@SearchBean(tables = "tb_arms")
@Schema(name = "Arms", description = "$!{table.comment}")
public class Arms implements Serializable {

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
     * 材料
     */
    private String material;

    /**
     * 类型
     */
    private String type;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 描述
     */
    private String description;

    /**
     * 扶手距座面高度(mm)
     */
    private Integer armHeightFromSeat;

    /**
     * 扶手距地面高度(mm)
     */
    private Integer armHeightFromFloor;

}
