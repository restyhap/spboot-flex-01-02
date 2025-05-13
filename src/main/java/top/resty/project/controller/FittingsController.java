package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.Fittings;
import top.resty.project.service.FittingsService;
import top.resty.project.vo.ResultVO;

import java.util.Map;

/**
 * 配件信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/fittings")
public class FittingsController {

    @Autowired
    private FittingsService fittingsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索配件信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(Fittings.class, flat));
    }

    /**
     * 添加配件信息表。
     *
     * @param fittings 配件信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Fittings fittings) {
        return ResultVO.success(fittingsService.save(fittings));
    }

    /**
     * 根据主键删除配件信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(fittingsService.removeById(id));
    }

    /**
     * 根据主键更新配件信息表。
     *
     * @param fittings 配件信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Fittings fittings) {
        return ResultVO.success(fittingsService.updateById(fittings));
    }

    /**
     * 查询所有配件信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(fittingsService.list());
    }

    /**
     * 根据配件信息表主键获取详细信息。
     *
     * @param id 配件信息表主键
     * @return 配件信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(fittingsService.getById(id));
    }

    /**
     * 分页查询配件信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Fittings> page) {
        return ResultVO.success(fittingsService.page(page));
    }

}
