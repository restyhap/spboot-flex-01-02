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

/**
 * 包装信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_carton_details")
@SearchBean(tables = "tb_carton_details")
public class CartonDetails implements Serializable {

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
     * 包装箱宽度(mm)
     */
    private Integer width;

    /**
     * 包装箱深度(mm)
     */
    private Integer depth;

    /**
     * 包装箱高度(mm)
     */
    private Integer height;

    /**
     * 纸板类型
     */
    private String boardType;

    /**
     * 每箱数量
     */
    private Integer itemsPerCarton;

    /**
     * 箱体体积(m³)
     */
    private BigDecimal cartonVolume;

}
