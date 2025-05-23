package top.resty.spboot.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *    配置 https 访问
 * </p>
 *
 * @author : resty
 * @since : 2024年11月18 - 10:14
 */
// @Configuration
public class HttpsConfig {

  @Bean
  public Connector connector(){
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setSecure(false); // 关闭安全协议
    connector.setPort(8080);
    connector.setRedirectPort(443);
    return connector;
  }

  public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
      @Override
      protected void postProcessContext(Context context) {
        SecurityConstraint constraint = new SecurityConstraint();
        constraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        constraint.addCollection(collection);
        context.addConstraint(constraint);
      }
    };
    factory.addAdditionalTomcatConnectors(connector);
    return factory;
  }

}
