package top.resty.spboot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年03月04 - 17:34
 */
@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("标题")
            .contact(new Contact())
            .description("我的API文档")
            .version("v1")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))

        .externalDocs(new ExternalDocumentation()
            .description("外部文档")
            .url("https://springshop.wiki.github.org/docs"));
  }
}
