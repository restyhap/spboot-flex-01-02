package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.GasLift;
import top.resty.spboot.service.GasLiftService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 气压棒信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/gasLift")
public class GasLiftController {

    @Autowired
    private GasLiftService gasLiftService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索气压棒信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(GasLift.class, flat));
    }

    /**
     * 添加气压棒信息表。
     *
     * @param gasLift 气压棒信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody GasLift gasLift) {
        return ResultVO.success(gasLiftService.save(gasLift));
    }

    /**
     * 根据主键删除气压棒信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(gasLiftService.removeById(id));
    }

    /**
     * 根据主键更新气压棒信息表。
     *
     * @param gasLift 气压棒信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody GasLift gasLift) {
        return ResultVO.success(gasLiftService.updateById(gasLift));
    }

    /**
     * 查询所有气压棒信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(gasLiftService.list());
    }

    /**
     * 根据气压棒信息表主键获取详细信息。
     *
     * @param id 气压棒信息表主键
     * @return 气压棒信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(gasLiftService.getById(id));
    }

    /**
     * 分页查询气压棒信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<GasLift> page) {
        return ResultVO.success(gasLiftService.page(page));
    }

}
