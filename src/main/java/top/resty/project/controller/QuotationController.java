package top.resty.project.controller;

import cn.zhxu.bs.BeanSearcher;
import cn.zhxu.bs.SearchResult;
import cn.zhxu.bs.util.MapBuilder;
import cn.zhxu.bs.util.MapUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.resty.project.entity.Quotation;
import top.resty.project.mapper.QuotationMapper;
import top.resty.project.service.QuotationService;
import top.resty.project.utils.InsertUtil;
import top.resty.project.vo.ResultVO;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 报价单 控制层。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Slf4j
@RestController
@RequestMapping("/quotation")
public class QuotationController {

  @Autowired
  private QuotationService quotationService;

  @Autowired
  private BeanSearcher beanSearcher;
  //绑定文件上传路径到uploadPath
  @Value("${config.upload-path}")
  private String uploadPath;

  @Autowired
  private QuotationMapper quotationMapper;

  /**
   * 搜索报价单。
   *
   * @param request 请求
   * @return 搜索结果
   */
  @GetMapping("search")
  public ResultVO search(HttpServletRequest request) {
    String keyword = request.getParameter("keyword");
    Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
    Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
    // 获取供应商名称参数（如果存在）
    String supplier = request.getParameter("supplier");

    MapBuilder builder = MapUtils.builder();

    // 根据关键字进行模糊查询
    if (keyword != null && !keyword.trim().isEmpty()) {
      builder.or(or -> or
          .field(Quotation::getId, keyword).op("ct")
          .field(Quotation::getSupplier, keyword).op("ct")
          .field(Quotation::getSupplierItemCode, keyword).op("ct")
          .field(Quotation::getSpecificationDetails, keyword).op("ct")
          .field(Quotation::getNetWeightGrossWeight, keyword).op("ct")
          .field(Quotation::getEffectiveVol, keyword).op("ct")
          .field(Quotation::getMoq, keyword).op("ct")
          .field(Quotation::getSalesContacts, keyword).op("ct")
          .field(Quotation::getValidPeriod, keyword).op("ct")
          .field(Quotation::getPort, keyword).op("ct")
          .field(Quotation::getRemark, keyword).op("ct")
      );
    }

    // 如果有供应商参数，添加精确匹配条件
    if (supplier != null && !supplier.trim().isEmpty()) {
      builder.field(Quotation::getSupplier, supplier).op("eq");
    }

    // 设置排序、分页等参数
    Map flat = builder
        .put("sort", "id")
        .put("order", "desc")
        .put("page", currentPage - 1)
        .put("size", pageSize)
        .build();

    SearchResult search = beanSearcher.search(Quotation.class, flat);

    return ResultVO.success(search);
  }

  /**
   * 添加报价单。
   *
   * @param quotation 报价单数据（JSON格式）
   * @param files     上传的文件列表
   * @return {@code true} 添加成功，{@code false} 添加失败
   */
  @PostMapping("save")
  public ResultVO save(@RequestBody Quotation quotation) throws IOException {
    quotation.setId(InsertUtil.id());
    return ResultVO.success(quotationService.save(quotation));
  }

  /**
   * 根据主键删除报价单。
   *
   * @param id 主键
   * @return {@code true} 删除成功，{@code false} 删除失败
   */
  @DeleteMapping("remove/{id}")
  public ResultVO remove(@PathVariable String id , HttpServletRequest request) {
    Quotation quotation = quotationService.getById(id);
    if (quotation != null) {
      String filePath = quotation.getImage();
      String s = request.getScheme() + "://" + request.getServerName()
          + ":" + request.getServerPort();
      filePath = filePath.replace(s, "");
      // 假设字段名为attachmentPath
      if (filePath != null && !filePath.isEmpty()) {
        File file = new File(uploadPath + filePath);
        if (file.exists()) {
          file.delete();
        }
      }
    }
    return ResultVO.success(quotationService.removeById(id));
  }

  /**
   * 根据主键更新报价单。
   *
   * @param quotation 报价单
   * @return {@code true} 更新成功，{@code false} 更新失败
   */
  @PutMapping("update")
  public ResultVO update(@RequestBody Quotation quotation) {
    return ResultVO.success(quotationService.updateById(quotation));
  }

  /**
   * 查询所有报价单。
   *
   * @return 所有数据
   */
  @GetMapping("list")
  public ResultVO list() {
    return ResultVO.success(quotationService.list());
  }

  /**
   * 根据报价单主键获取详细信息。
   *
   * @param id 报价单主键
   * @return 报价单详情
   */
  @GetMapping("getInfo/{id}")
  public ResultVO getInfo(@PathVariable String id) {
    return ResultVO.success(quotationService.getById(id));
  }

  /**
   * 分页查询报价单。
   *
   * @param page 分页对象
   * @return 分页对象
   */
  @GetMapping("page")
  public ResultVO page(Page<Quotation> page) {
    // 创建 QueryWrapper 并设置倒序排序
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.orderBy("id desc");  // 按ID倒序排序

    // 使用 paginate 方法进行分页查询
    Page<Quotation> resultPage = quotationService.page(page, queryWrapper);
    return ResultVO.success(resultPage);
  }


}
