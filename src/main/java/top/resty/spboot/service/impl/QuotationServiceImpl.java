package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.Quotation;
import top.resty.spboot.mapper.QuotationMapper;
import top.resty.spboot.service.QuotationService;
import org.springframework.stereotype.Service;

/**
 * 报价单 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class QuotationServiceImpl extends ServiceImpl<QuotationMapper, Quotation> implements QuotationService {

}
