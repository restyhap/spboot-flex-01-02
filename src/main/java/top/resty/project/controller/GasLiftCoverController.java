package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.GasLiftCover;
import top.resty.project.service.GasLiftCoverService;
import top.resty.project.vo.ResultVO;

import java.util.Map;

/**
 * 气压罩信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/gasLiftCover")
public class GasLiftCoverController {

    @Autowired
    private GasLiftCoverService gasLiftCoverService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索气压罩信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(GasLiftCover.class, flat));
    }

    /**
     * 添加气压罩信息表。
     *
     * @param gasLiftCover 气压罩信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody GasLiftCover gasLiftCover) {
        return ResultVO.success(gasLiftCoverService.save(gasLiftCover));
    }

    /**
     * 根据主键删除气压罩信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(gasLiftCoverService.removeById(id));
    }

    /**
     * 根据主键更新气压罩信息表。
     *
     * @param gasLiftCover 气压罩信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody GasLiftCover gasLiftCover) {
        return ResultVO.success(gasLiftCoverService.updateById(gasLiftCover));
    }

    /**
     * 查询所有气压罩信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(gasLiftCoverService.list());
    }

    /**
     * 根据气压罩信息表主键获取详细信息。
     *
     * @param id 气压罩信息表主键
     * @return 气压罩信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(gasLiftCoverService.getById(id));
    }

    /**
     * 分页查询气压罩信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<GasLiftCover> page) {
        return ResultVO.success(gasLiftCoverService.page(page));
    }

}
