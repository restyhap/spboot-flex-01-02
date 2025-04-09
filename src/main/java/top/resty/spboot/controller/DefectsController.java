package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.Defects;
import top.resty.spboot.service.DefectImagesService;
import top.resty.spboot.service.DefectsService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 缺陷记录表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/defects")
public class DefectsController {

    @Autowired
    private DefectsService defectsService;

    @Autowired
    private BeanSearcher beanSearcher ;
    @Autowired
    private DefectImagesService defectsImageService;

    /**
     * 搜索缺陷记录表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(Defects.class, flat));
    }

    /**
     * 添加缺陷记录表。
     *
     * @param defects 缺陷记录表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Defects defects) {
        return ResultVO.success(defectsService.save(defects));
    }

    /**
     * 根据主键删除缺陷记录表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Transactional
    public ResultVO remove(@PathVariable String id) {
        defectsImageService.removeByDefectId(id); // 删除关联的缺陷图片
        return ResultVO.success(defectsService.removeById(id));
    }

    /**
     * 根据主键更新缺陷记录表。
     *
     * @param defects 缺陷记录表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Defects defects) {
        return ResultVO.success(defectsService.updateById(defects));
    }

    /**
     * 查询所有缺陷记录表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(defectsService.list());
    }

    /**
     * 根据缺陷记录表主键获取详细信息。
     *
     * @param id 缺陷记录表主键
     * @return 缺陷记录表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(defectsService.getById(id));
    }

    @GetMapping("getInfoByReportId/{id}")
    public ResultVO getInfoByReportId(@PathVariable String id) {
        return ResultVO.success(defectsService.getInfoByReportId(id));
    }


    /**
     * 分页查询缺陷记录表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Defects> page) {
        return ResultVO.success(defectsService.page(page));
    }

}
