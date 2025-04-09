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
 *  实体类。
 *
 * @author resty-mac
 * @since 2025-02-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_permissions")
@SearchBean(tables = "tb_permissions")
public class Permissions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 角色类型：0-管理员，1-供应商，2-员工
     */
    private Integer roleType;

    /**
     * 规格表-查看：0-禁用，1-启用
     */
    private Integer prodView;

    /**
     * 规格表-创建：0-禁用，1-启用
     */
    private Integer prodCreate;

    /**
     * 规格表-编辑：0-禁用，1-启用
     */
    private Integer prodEdit;

    /**
     * 规格表-删除：0-禁用，1-启用
     */
    private Integer prodDelete;

    /**
     * 质检表-查看：0-禁用，1-启用
     */
    private Integer specView;

    /**
     * 质检表-创建：0-禁用，1-启用
     */
    private Integer specCreate;

    /**
     * 质检表-编辑：0-禁用，1-启用
     */
    private Integer specEdit;

    /**
     * 质检表-删除：0-禁用，1-启用
     */
    private Integer specDelete;

    /**
     * 报价单-查看：0-禁用，1-启用
     */
    private Integer quoteView;

    /**
     * 报价单-创建：0-禁用，1-启用
     */
    private Integer quoteCreate;

    /**
     * 报价单-编辑：0-禁用，1-启用
     */
    private Integer quoteEdit;

    /**
     * 报价单-删除：0-禁用，1-启用
     */
    private Integer quoteDelete;

    /**
     * 系统设置-用户管理：0-禁用，1-启用
     */
    private Integer settingsUsers;

    /**
     * 系统设置-权限设置：0-禁用，1-启用
     */
    private Integer settingsPermissions;

}
