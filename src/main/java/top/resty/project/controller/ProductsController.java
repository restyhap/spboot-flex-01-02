package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapBuilder;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.Products;
import top.resty.project.service.ProductsService;
import top.resty.project.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * 产品基本信息表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索产品基本信息表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        String keyword = request.getParameter("keyword");
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        // 获取供应商名称参数（如果存在）
        String supplier = request.getParameter("supplier");

        MapBuilder builder = MapUtils.builder();

        // 如果有供应商参数，添加精确匹配条件（供应商用户查询时使用）
        if (supplier != null && !supplier.trim().isEmpty()) {
            builder.field(Products::getSupplier, supplier).op("eq");
        }

        // 根据关键字进行模糊查询
        if (keyword != null && !keyword.trim().isEmpty()) {
            builder.or(or -> or
                .field(Products::getTccode, keyword).op("ct")
                .field(Products::getSupplier, keyword).op("ct")
                .field(Products::getSupplierCode, keyword).op("ct")
            );
        }

        // 设置排序、分页等参数
        Map flat = builder
            .put("sort", "id")
            .put("order", "desc")
            .put("page", currentPage - 1)
            .put("size", pageSize)
            .build();

        List list = beanSearcher.searchList(Products.class, flat);

        return ResultVO.success(list);
    }

    /**
     * 添加产品基本信息表。
     *
     * @param products 产品基本信息表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Products products) {
        return ResultVO.success(productsService.save(products));
    }

    /**
     * 根据主键删除产品基本信息表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(productsService.removeById(id));
    }

    /**
     * 根据主键更新产品基本信息表。
     *
     * @param products 产品基本信息表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Products products) {
        return ResultVO.success(productsService.updateById(products));
    }

    /**
     * 查询所有产品基本信息表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(productsService.list());
    }

    /**
     * 根据产品基本信息表主键获取详细信息。
     *
     * @param id 产品基本信息表主键
     * @return 产品基本信息表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(productsService.getById(id));
    }

    /**
     * 分页查询产品基本信息表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Products> page) {
        return ResultVO.success(productsService.page(page,new QueryWrapper().orderBy("id desc")));
    }

}
