package top.resty.project.config;

import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年11月28 - 15:27
 */
@Configuration
public class GsonConfig  {

  @Bean
  public GsonBuilder initGson(){
    return new GsonBuilder();
  }

}
