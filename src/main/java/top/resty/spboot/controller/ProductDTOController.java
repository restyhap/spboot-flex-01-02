package top.resty.spboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.spboot.dto.ProductDTO;
import top.resty.spboot.service.ProductDTOService;
import top.resty.spboot.vo.ResultVO;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月18 - 16:15
 */
@RestController
@RequestMapping("/productDto")
public class ProductDTOController {

  @Autowired
  private ProductDTOService productDTOService;
  @PostMapping("/save")
  public ResultVO save(@RequestBody ProductDTO productDTO) {
    return ResultVO.success(productDTOService.save(productDTO));
  }

  @GetMapping("/get/{id}")
  public ResultVO get(@PathVariable String id){
    return  ResultVO.success(productDTOService.getProductDTOById(Long.valueOf(id)));
  }

  @DeleteMapping("/delete/{id}")
  public ResultVO delete(@PathVariable String id){
    return  ResultVO.success(productDTOService.removeProductDTOById(Long.valueOf(id)));
  }

}
