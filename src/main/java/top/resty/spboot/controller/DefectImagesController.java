package top.resty.spboot.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import top.resty.spboot.entity.DefectImages;
import top.resty.spboot.service.DefectImagesService;
import org.springframework.web.bind.annotation.RestController;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import top.resty.spboot.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 缺陷图片关联表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/defectImages")
public class DefectImagesController {

    @Autowired
    private DefectImagesService defectImagesService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索缺陷图片关联表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(DefectImages.class, flat));
    }

    /**
     * 添加缺陷图片关联表。
     *
     * @param defectImages 缺陷图片关联表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody DefectImages defectImages) {
        return ResultVO.success(defectImagesService.save(defectImages));
    }

    /**
     * 根据主键删除缺陷图片关联表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(defectImagesService.removeById(id));
    }

    /**
     * 根据主键更新缺陷图片关联表。
     *
     * @param defectImages 缺陷图片关联表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody DefectImages defectImages) {
        return ResultVO.success(defectImagesService.updateById(defectImages));
    }

    /**
     * 查询所有缺陷图片关联表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(defectImagesService.list());
    }

    /**
     * 根据缺陷图片关联表主键获取详细信息。
     *
     * @param id 缺陷图片关联表主键
     * @return 缺陷图片关联表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(defectImagesService.getById(id));
    }

    /**
     * 分页查询缺陷图片关联表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<DefectImages> page) {
        return ResultVO.success(defectImagesService.page(page));
    }

}
