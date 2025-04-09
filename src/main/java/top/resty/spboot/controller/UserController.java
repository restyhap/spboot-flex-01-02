package top.resty.spboot.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.entity.User;
import top.resty.spboot.service.UserService;
import top.resty.spboot.vo.ResultVO;

import java.util.Map;

/**
 *  控制层。
 *
 * @author resty-mac
 * @since 2025-02-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanSearcher beanSearcher ;

    @PostMapping("login")
    public ResultVO login(@RequestBody User user , HttpServletRequest request) {
        Map<String, Object> flat = MapUtils.flat(request.getParameterMap());
        flat.put("username",user.getUsername());
        flat.put("password",user.getPassword());
        return ResultVO.success(beanSearcher.searchList(User.class, flat));
    }

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
        flat.put("order","desc");
        return ResultVO.success(beanSearcher.searchList(User.class, flat));
    }

    /**
     * 添加。
     *
     * @param user
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public ResultVO save(@RequestBody User user) {
        return ResultVO.success(userService.save(user));
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        return ResultVO.success(userService.removeById(id));
    }

    /**
     * 根据主键更新。
     *
     * @param user
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public ResultVO update(@RequestBody User user) {
        return ResultVO.success(userService.updateById(user));
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public ResultVO list() {
        return ResultVO.success(userService.list());
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public ResultVO getInfo(@PathVariable String id) {
        return ResultVO.success(userService.getById(id));
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public ResultVO page(Page<User> page) {
        return ResultVO.success(userService.page(page));
    }

}
