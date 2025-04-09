/*
 * @Author: resty restyhap@hotmail.com
 * @Date: 2025-02-11 21:17:50
 * @LastEditors: resty restyhap@hotmail.com
 * @LastEditTime: 2025-02-16 17:43:50
 * @FilePath: /yarn-vite-web-01-02/Users/resty-mac/02-workspace/99_project/2025.01/spboot-flex-01-02/src/main/java/top/resty/spboot/mapper/DefectsMapper.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package top.resty.spboot.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.resty.spboot.entity.Defects;

import java.util.List;

/**
 * 缺陷记录表 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface DefectsMapper extends BaseMapper<Defects> {

  @Select("""
    select  id , report_id as reportId , defect_title as  defectTitle, defect_description as defectDescription , 
    improvement_suggestion as improvementSuggestion ,inspector, created_at as createdAt , updated_at as updatedAt  
    from tb_defects where report_id = #{id} 
  """)
  List<Defects> getInfoByReportId(String id);

  /**
   * 批量保存缺陷记录
   *
   * @param defectsList 缺陷记录列表
   * @return 每条记录影响的行数数组
   */
  @Insert("""
    <script>
      INSERT INTO tb_defects (id, report_id, defect_title,defect_description, improvement_suggestion, inspector) VALUES 
      <foreach collection='list' item='item' separator=','>
          (#{item.id}, #{item.reportId}, #{item.defectTitle}, #{item.defectDescription},#{item.improvementSuggestion},#{item.inspector})
      </foreach>
    </script>
    """)
  int batchSave(List<Defects> defectsList);

  @Delete("""
    <script>
      delete from tb_defects where id in
      <foreach collection = 'defectIds' item='id' open='(' close=')' separator=','>
        #{id}  
      </foreach> 
    </script>
  """)
  int deleteByIds(List<String> defectIds);
}
