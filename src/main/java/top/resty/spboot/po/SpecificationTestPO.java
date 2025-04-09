package top.resty.spboot.po;

import cn.zhxu.bs.bean.DbField;
import cn.zhxu.bs.bean.SearchBean;
import lombok.Data;


/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年01月05 - 16:11
 */
@Data
@SearchBean(
    tables = "tb_specification ts inner join tb_test tt on tt.sid = ts.id",
    autoMapTo = "ts"
)
public class SpecificationTestPO {

  // Specification表字段映射
  @DbField("ts.id")
  private Long id;

  @DbField("ts.tccode")
  private String tccode;

  @DbField("ts.supplier")
  private String supplier;

  @DbField("ts.supplier_code")
  private String supplierCode;

  @DbField("ts.supplier_name")
  private String supplierName;

  //Test表字段
  @DbField("tt.id")
  private Integer testId;

  @DbField("tt.sid")
  private Integer sid;

  @DbField("tt.name")
  private String name;

  // 转换为实体对象
  /*public Specification getSpecification() {
    Specification spec = new Specification();
    spec.setId(this.id);
    spec.setTccode(this.tccode);
    spec.setSupplier(this.supplier);
    spec.setSupplierCode(this.supplierCode);
    spec.setSupplierName(this.supplierName);
    return spec;
  }

  public Test getTest() {
    Test test = new Test();
    test.setId(this.testId);
    test.setSid(this.sid);
    test.setName(this.name);
    return test;
  }*/

}
