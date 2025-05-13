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
 * 背部内部结构表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_back_inner_components")
@SearchBean(tables = "tb_back_inner_components")
public class BackInnerComponents implements Serializable {

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
     * 材料代码
     */
    private String materialCode;

    /**
     * 厚度(mm)
     */
    private Integer thickness;

    /**
     * 层数
     */
    private Integer layersCount;

    /**
     * 尺寸规格
     */
    private String dimensions;

}
