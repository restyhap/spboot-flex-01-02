package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.Arms;
import top.resty.spboot.service.ArmsService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 扶手信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/arms")
public class ArmsController {

    @Autowired
    private ArmsService armsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索扶手信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    @Operation(summary = "搜索扶手信息表")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(Arms.class, flat));
    }

    /**
     * 添加扶手信息表。
     *
     * @param arms 扶手信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Arms arms) {
        return ResultVO.success(armsService.save(arms));
    }

    /**
     * 根据主键删除扶手信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(armsService.removeById(id));
    }

    /**
     * 根据主键更新扶手信息表。
     *
     * @param arms 扶手信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Arms arms) {
        return ResultVO.success(armsService.updateById(arms));
    }

    /**
     * 查询所有扶手信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(armsService.list());
    }

    /**
     * 根据扶手信息表主键获取详细信息。
     *
     * @param id 扶手信息表主键
     * @return 扶手信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(armsService.getById(id));
    }

    /**
     * 分页查询扶手信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Arms> page) {
        return ResultVO.success(armsService.page(page));
    }

}
