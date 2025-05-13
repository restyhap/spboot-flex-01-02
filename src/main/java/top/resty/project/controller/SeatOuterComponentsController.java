package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.SeatOuterComponents;
import top.resty.project.service.SeatOuterComponentsService;
import top.resty.project.vo.ResultVO;

import java.util.Map;

/**
 * 座椅外部结构表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/seatOuterComponents")
public class SeatOuterComponentsController {

    @Autowired
    private SeatOuterComponentsService seatOuterComponentsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索座椅外部结构表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(SeatOuterComponents.class, flat));
    }

    /**
     * 添加座椅外部结构表。
     *
     * @param seatOuterComponents 座椅外部结构表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody SeatOuterComponents seatOuterComponents) {
        return ResultVO.success(seatOuterComponentsService.save(seatOuterComponents));
    }

    /**
     * 根据主键删除座椅外部结构表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(seatOuterComponentsService.removeById(id));
    }

    /**
     * 根据主键更新座椅外部结构表。
     *
     * @param seatOuterComponents 座椅外部结构表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody SeatOuterComponents seatOuterComponents) {
        return ResultVO.success(seatOuterComponentsService.updateById(seatOuterComponents));
    }

    /**
     * 查询所有座椅外部结构表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(seatOuterComponentsService.list());
    }

    /**
     * 根据座椅外部结构表主键获取详细信息。
     *
     * @param id 座椅外部结构表主键
     * @return 座椅外部结构表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(seatOuterComponentsService.getById(id));
    }

    /**
     * 分页查询座椅外部结构表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<SeatOuterComponents> page) {
        return ResultVO.success(seatOuterComponentsService.page(page));
    }

}
