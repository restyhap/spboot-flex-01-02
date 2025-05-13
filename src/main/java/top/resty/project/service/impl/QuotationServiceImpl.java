package top.resty.project.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.resty.project.entity.Quotation;
import top.resty.project.mapper.QuotationMapper;
import top.resty.project.service.QuotationService;

/**
 * 报价单 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class QuotationServiceImpl extends ServiceImpl<QuotationMapper, Quotation> implements QuotationService {

}
