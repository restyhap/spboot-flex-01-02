package top.resty.spboot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import top.resty.spboot.entity.DefectImages;
import top.resty.spboot.mapper.DefectImagesMapper;
import top.resty.spboot.service.DefectImagesService;
import org.springframework.stereotype.Service;

/**
 * 缺陷图片关联表 服务层实现。
 *
 * @author resty-mac
 * @since 2025-02-11
 */
@Service
public class DefectImagesServiceImpl extends ServiceImpl<DefectImagesMapper, DefectImages> implements DefectImagesService {

}
