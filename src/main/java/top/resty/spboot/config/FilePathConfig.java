package top.resty.spboot.config;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年11月19 - 10:25
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件路径配置，可以直接访问
 * 映射的路径后面必须加/，否则访问不到
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

  @Value("${config.upload-path}")
  private String uploadPath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //和页面有关的静态目录都放在项目的static目录下
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/templates/")
        .addResourceLocations("classpath:/images/")
        .addResourceLocations("file:"+uploadPath);
  }

}
