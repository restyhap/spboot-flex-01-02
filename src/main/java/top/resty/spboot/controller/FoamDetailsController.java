package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.FoamDetails;
import top.resty.spboot.service.FoamDetailsService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 泡棉信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/foamDetails")
public class FoamDetailsController {

    @Autowired
    private FoamDetailsService foamDetailsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索泡棉信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(FoamDetails.class, flat));
    }

    /**
     * 添加泡棉信息表。
     *
     * @param foamDetails 泡棉信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody FoamDetails foamDetails) {
        return ResultVO.success(foamDetailsService.save(foamDetails));
    }

    /**
     * 根据主键删除泡棉信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(foamDetailsService.removeById(id));
    }

    /**
     * 根据主键更新泡棉信息表。
     *
     * @param foamDetails 泡棉信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody FoamDetails foamDetails) {
        return ResultVO.success(foamDetailsService.updateById(foamDetails));
    }

    /**
     * 查询所有泡棉信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(foamDetailsService.list());
    }

    /**
     * 根据泡棉信息表主键获取详细信息。
     *
     * @param id 泡棉信息表主键
     * @return 泡棉信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(foamDetailsService.getById(id));
    }

    /**
     * 分页查询泡棉信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<FoamDetails> page) {
        return ResultVO.success(foamDetailsService.page(page));
    }

}
