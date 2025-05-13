package top.resty.project.service;

import com.mybatisflex.core.service.IService;
import top.resty.project.dto.ProductDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月18 - 16:16
 */
public interface ProductDTOService extends IService<ProductDTO> {
  boolean save(ProductDTO productDTO);

  ProductDTO getProductDTOById(Long productId);

  boolean removeProductDTOById(Long aLong);
}
