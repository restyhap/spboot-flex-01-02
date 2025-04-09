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
