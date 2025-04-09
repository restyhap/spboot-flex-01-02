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
import top.resty.spboot.entity.ProductionLogistics;
import top.resty.spboot.service.ProductionLogisticsService;
import org.springframework.web.bind.annotation.RestController;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import top.resty.spboot.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 生产和物流信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/productionLogistics")
public class ProductionLogisticsController {

    @Autowired
    private ProductionLogisticsService productionLogisticsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索生产和物流信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(ProductionLogistics.class, flat));
    }

    /**
     * 添加生产和物流信息表。
     *
     * @param productionLogistics 生产和物流信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody ProductionLogistics productionLogistics) {
        return ResultVO.success(productionLogisticsService.save(productionLogistics));
    }

    /**
     * 根据主键删除生产和物流信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(productionLogisticsService.removeById(id));
    }

    /**
     * 根据主键更新生产和物流信息表。
     *
     * @param productionLogistics 生产和物流信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody ProductionLogistics productionLogistics) {
        return ResultVO.success(productionLogisticsService.updateById(productionLogistics));
    }

    /**
     * 查询所有生产和物流信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(productionLogisticsService.list());
    }

    /**
     * 根据生产和物流信息表主键获取详细信息。
     *
     * @param id 生产和物流信息表主键
     * @return 生产和物流信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(productionLogisticsService.getById(id));
    }

    /**
     * 分页查询生产和物流信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<ProductionLogistics> page) {
        return ResultVO.success(productionLogisticsService.page(page));
    }

}
