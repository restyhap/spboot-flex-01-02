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
 * 规格图片表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_product_images")
@SearchBean(tables = "tb_product_images")
public class ProductImages implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 序列
     */
    @Id
    private String id;

    /**
     * 规格表ID
     */
    private String prodId;

    /**
     * 正视图路径
     */
    private String frontImgPath;

    /**
     * 侧视图路径
     */
    private String sideImgPath;

    /**
     * 背视图路径
     */
    private String backImgPath;

    /**
     * 角视图路径
     */
    private String angleImgPath;

}
