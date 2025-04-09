package top.resty.spboot.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月11 - 16:58
 */
@Data
public class QuoteVO {

  private String id;

  private String supplier;

  private String supplierItemCode;

  private String specificationDetails;

  private LocalDateTime sampleLeadTime;

  private BigDecimal fobPrice;

  private String salesContacts;

  private LocalDateTime createTime;

}
