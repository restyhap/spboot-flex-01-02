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
import top.resty.spboot.entity.ProductImages;
import top.resty.spboot.service.ProductImagesService;
import org.springframework.web.bind.annotation.RestController;
import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import top.resty.spboot.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 规格图片表 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@RestController
@RequestMapping("/productImages")
public class ProductImagesController {

    @Autowired
    private ProductImagesService productImagesService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索规格图片表。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(ProductImages.class, flat));
    }

    /**
     * 添加规格图片表。
     *
     * @param productImages 规格图片表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody ProductImages productImages) {
        return ResultVO.success(productImagesService.save(productImages));
    }

    /**
     * 根据主键删除规格图片表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(productImagesService.removeById(id));
    }

    /**
     * 根据主键更新规格图片表。
     *
     * @param productImages 规格图片表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody ProductImages productImages) {
        return ResultVO.success(productImagesService.updateById(productImages));
    }

    /**
     * 查询所有规格图片表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(productImagesService.list());
    }

    /**
     * 根据规格图片表主键获取详细信息。
     *
     * @param id 规格图片表主键
     * @return 规格图片表详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(productImagesService.getById(id));
    }

    /**
     * 分页查询规格图片表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<ProductImages> page) {
        return ResultVO.success(productImagesService.page(page));
    }

}
