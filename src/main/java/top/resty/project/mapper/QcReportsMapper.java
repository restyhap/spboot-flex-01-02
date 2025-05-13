/*
 * @Author: resty restyhap@hotmail.com
 * @Date: 2025-02-11 21:17:50
 * @LastEditors: resty restyhap@hotmail.com
 * @LastEditTime: 2025-02-16 17:40:58
 * @FilePath: /yarn-vite-web-01-02/Users/resty-mac/02-workspace/99_project/2025.01/spboot-flex-01-02/src/main/java/top/resty/spboot/mapper/QcReportsMapper.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package top.resty.project.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.resty.project.entity.QcReports;

/**
 * 质检报告基本信息表 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface QcReportsMapper extends BaseMapper<QcReports> {

  /**
   * 保存质检报告
   *
   * @param qcReports 质检报告实体
   * @return 影响的行数
   */
  @Insert("INSERT INTO tb_qc_reports (id, model_code, factory_code, supplier, client, po_number, inspection_date, order_qty, report_date, inspect_qty, qc_officer, pass_fail, second_qc_date, comments, stocks_in_warehouse, sampling_of_products_quantity, shipping_marks, barcode, packing_outside, packing_inside, chair_components_packed, chair_components_unpacked, fitting_pack_packed, fitting_pack_unpacked, production_label, assembly_instructions, image_of_components_seat, image_of_components_back, image_of_components_base, image_of_components_castors, image_of_components_gas_lift_cover, image_of_components_gas_lift_stamp, image_of_components_armrest, image_of_component_mechanism, image_of_components_headrest, image_of_product_built_front, image_of_product_built_side, image_of_product_built_back, image_of_product_built_45_degree, front_image_of_product_built_compare_1, front_image_of_product_built_compare_2, function_check_seat_height_extension, function_check_mechanism_adjustment, function_check_armrest_adjustment, function_check_headrest_adjustment, function_check_other1, function_check_other2) VALUES (#{id}, #{modelCode}, #{factoryCode}, #{supplier}, #{client}, #{poNumber}, #{inspectionDate}, #{orderQty}, #{reportDate}, #{inspectQty}, #{qcOfficer}, #{passFail}, #{secondQcDate}, #{comments}, #{stocksInWarehouse}, #{samplingOfProductsQuantity}, #{shippingMarks}, #{barcode}, #{packingOutside}, #{packingInside}, #{chairComponentsPacked}, #{chairComponentsUnpacked}, #{fittingPackPacked}, #{fittingPackUnpacked}, #{productionLabel}, #{assemblyInstructions}, #{imageOfComponentsSeat}, #{imageOfComponentsBack}, #{imageOfComponentsBase}, #{imageOfComponentsCastors}, #{imageOfComponentsGasLiftCover}, #{imageOfComponentsGasLiftStamp}, #{imageOfComponentsArmrest}, #{imageOfComponentMechanism}, #{imageOfComponentsHeadrest}, #{imageOfProductBuiltFront}, #{imageOfProductBuiltSide}, #{imageOfProductBuiltBack}, #{imageOfProductBuilt45Degree}, #{frontImageOfProductBuiltCompare1}, #{frontImageOfProductBuiltCompare2}, #{functionCheckSeatHeightExtension}, #{functionCheckMechanismAdjustment}, #{functionCheckArmrestAdjustment}, #{functionCheckHeadrestAdjustment}, #{functionCheckOther1}, #{functionCheckOther2})")
  int save(QcReports qcReports);

}
