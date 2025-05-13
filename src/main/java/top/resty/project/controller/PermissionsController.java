package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.Permissions;
import top.resty.project.service.PermissionsService;
import top.resty.project.vo.ResultVO;

import java.util.Map;

/**
 *  控制层。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private BeanSearcher beanSearcher ;

    /**
     * 搜索。
     *
     * @param request 请求
     * @return 搜索结果
     */
    @GetMapping("search")
    public ResultVO search (HttpServletRequest request ) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("sort","id");
        return ResultVO.success(beanSearcher.searchList(Permissions.class, flat));
    }

    /**
     * 添加。
     *
     * @param permissions
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody Permissions permissions) {
        return ResultVO.success(permissionsService.save(permissions));
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(permissionsService.removeById(id));
    }

    /**
     * 根据主键更新。
     *
     * @param permissions
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody Permissions permissions) {
        return ResultVO.success(permissionsService.updateById(permissions));
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(permissionsService.list());
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(permissionsService.getById(id));
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<Permissions> page) {
        return ResultVO.success(permissionsService.page(page));
    }

}
