package top.resty.spboot;

import cn.hutool.core.io.resource.ClassPathResource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

  @Value("${config.url}")
  private String url;
  @Value("${config.class-name}")
  private String driverClassName;
  @Value("${config.user}")
  private String userName;
  @Value("${config.pwd}")
  private String pwd;
  @Value("${config.path}")
  private String path;


  @Test
void contextLoads() {
    //配置数据源
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(userName);
    dataSource.setPassword(pwd);

    GlobalConfig globalConfig = createGlobalConfigUseStyle();
    //通过 datasource 和 globalConfig 创建代码生成器
    Generator generator = new Generator(dataSource, globalConfig);

    //生成代码
    generator.generate();

    System.out.println("生成完成");
  }

  public GlobalConfig createGlobalConfigUseStyle() {
    //创建配置内容
    GlobalConfig globalConfig = new GlobalConfig();

    //设置根包
    globalConfig.getPackageConfig()
        .setBasePackage(path);

    //设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
    globalConfig.getStrategyConfig()
        .setTablePrefix("tb_");
    // .setGenerateTable("all");

    // 自定义模板
    globalConfig.getTemplateConfig()
        .setEntity(new ClassPathResource("classpath:templates/entity.tpl").getPath())
        .setController(new ClassPathResource("classpath:templates/controller.tpl").getPath());
        // .setServiceImpl(new ClassPathResource("classpath:templates/serviceImpl.tpl").getPath());


    //设置生成 entity 并启用 Lombok
    globalConfig.enableEntity()
        .setWithLombok(true)
        .setJdkVersion(17)

        // .setOverwriteEnable(true)
        ;

    //设置生成 mapper
    // globalConfig.enableMapper().setMapperAnnotation(true).setOverwriteEnable(true);
    globalConfig.enableMapper().setMapperAnnotation(true);

    // 设置生成 service
    // globalConfig.enableService().setOverwriteEnable(true);
    globalConfig.enableService();

    // 设置生成 serviceImpl
    // globalConfig.enableServiceImpl().setOverwriteEnable(true);
    globalConfig.enableServiceImpl();

    // 设置生成 controller
    // globalConfig.enableController().setOverwriteEnable(true);
    globalConfig.enableController();

    return globalConfig;
  }

}
