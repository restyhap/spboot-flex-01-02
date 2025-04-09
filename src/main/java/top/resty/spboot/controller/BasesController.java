package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.Bases;
import top.resty.spboot.service.BasesService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 底座信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/bases")
public class BasesController {

    @Autowired
    private BasesService basesService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索底座信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(Bases.class, flat));
    }

    /**
     * 添加底座信息表。
     *
     * @param bases 底座信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Bases bases) {
        return ResultVO.success(basesService.save(bases));
    }

    /**
     * 根据主键删除底座信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(basesService.removeById(id));
    }

    /**
     * 根据主键更新底座信息表。
     *
     * @param bases 底座信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Bases bases) {
        return ResultVO.success(basesService.updateById(bases));
    }

    /**
     * 查询所有底座信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(basesService.list());
    }

    /**
     * 根据底座信息表主键获取详细信息。
     *
     * @param id 底座信息表主键
     * @return 底座信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(basesService.getById(id));
    }

    /**
     * 分页查询底座信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Bases> page) {
        return ResultVO.success(basesService.page(page));
    }

}
