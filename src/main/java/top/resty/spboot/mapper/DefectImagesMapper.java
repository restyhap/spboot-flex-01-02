package top.resty.spboot.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.resty.spboot.entity.DefectImages;

import java.util.List;

/**
 * 缺陷图片关联表 映射层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Mapper
public interface DefectImagesMapper extends BaseMapper<DefectImages> {

    /**
     * 批量保存缺陷图片
     *
     * @param defectImagesList 缺陷图片列表
     * @return 每条记录影响的行数数组
     */
    @Insert("<script>" +
                "INSERT INTO tb_defect_images (id, defect_id, image_path) VALUES " +
                "<foreach collection='list' item='item' separator=','>" +
                    "(#{item.id}, #{item.defectId}, #{item.imagePath})" +
                "</foreach>" +
            "</script>")
    int batchSave(List<DefectImages> defectImagesList);

    @Delete("""
        <script>
            delete from tb_defect_images where defect_id in
            <foreach collection='defectIds' item='id' open='(' close=')' separator=',' > 
                #{id}
            </foreach>
        </script>  
    """)
    int deleteByDefectIds(List<String> defectIds);

    @Select("""
        select id , defect_id as defectId , image_path as imagePath , created_at as createdAt , updated_at as updatedAt 
        from tb_defect_images where defect_id = #{id}
    """)
    List<DefectImages> getInfoByDefectId(String id);

    @Delete("""
        delete from tb_defect_images where defect_id = #{id}
    """)
    int removeByDefectId(String id);
}
