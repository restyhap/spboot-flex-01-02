package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.ProductDimensions;
import top.resty.spboot.service.ProductDimensionsService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 * 产品尺寸表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/productDimensions")
public class ProductDimensionsController {

    @Autowired
    private ProductDimensionsService productDimensionsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索产品尺寸表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(ProductDimensions.class, flat));
    }

    /**
     * 添加产品尺寸表。
     *
     * @param productDimensions 产品尺寸表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody ProductDimensions productDimensions) {
        return ResultVO.success(productDimensionsService.save(productDimensions));
    }

    /**
     * 根据主键删除产品尺寸表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(productDimensionsService.removeById(id));
    }

    /**
     * 根据主键更新产品尺寸表。
     *
     * @param productDimensions 产品尺寸表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody ProductDimensions productDimensions) {
        return ResultVO.success(productDimensionsService.updateById(productDimensions));
    }

    /**
     * 查询所有产品尺寸表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(productDimensionsService.list());
    }

    /**
     * 根据产品尺寸表主键获取详细信息。
     *
     * @param id 产品尺寸表主键
     * @return 产品尺寸表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(productDimensionsService.getById(id));
    }

    /**
     * 分页查询产品尺寸表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<ProductDimensions> page) {
        return ResultVO.success(productDimensionsService.page(page));
    }

}
