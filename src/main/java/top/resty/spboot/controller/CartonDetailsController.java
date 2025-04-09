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
import top.resty.spboot.entity.CartonDetails;
import top.resty.spboot.service.CartonDetailsService;
import org.springframework.web.bind.annotation.RestController;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import top.resty.spboot.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 包装信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/cartonDetails")
public class CartonDetailsController {

    @Autowired
    private CartonDetailsService cartonDetailsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索包装信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(CartonDetails.class, flat));
    }

    /**
     * 添加包装信息表。
     *
     * @param cartonDetails 包装信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody CartonDetails cartonDetails) {
        return ResultVO.success(cartonDetailsService.save(cartonDetails));
    }

    /**
     * 根据主键删除包装信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(cartonDetailsService.removeById(id));
    }

    /**
     * 根据主键更新包装信息表。
     *
     * @param cartonDetails 包装信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody CartonDetails cartonDetails) {
        return ResultVO.success(cartonDetailsService.updateById(cartonDetails));
    }

    /**
     * 查询所有包装信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(cartonDetailsService.list());
    }

    /**
     * 根据包装信息表主键获取详细信息。
     *
     * @param id 包装信息表主键
     * @return 包装信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(cartonDetailsService.getById(id));
    }

    /**
     * 分页查询包装信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<CartonDetails> page) {
        return ResultVO.success(cartonDetailsService.page(page));
    }

}
