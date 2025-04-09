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
 * 机构信息表 实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_mechanism")
@SearchBean(tables = "tb_mechanism")
public class Mechanism implements Serializable {

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
     * 手柄数量
     */
    private Integer leversCount;

    /**
     * 锁定位置
     */
    private String lockingPositions;

    /**
     * 型号
     */
    private String modelNo;

    /**
     * 机构供应商
     */
    private String supplierName;

}
