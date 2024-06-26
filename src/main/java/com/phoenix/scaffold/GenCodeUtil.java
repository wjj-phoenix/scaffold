package com.phoenix.scaffold;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author wjj-phoenix
 * @since 2024-06-25
 */
public class GenCodeUtil {
    public static void main(String[] args) {
        // 配置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.73.100:3306/scaffold?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("Tv75aYT8@");

        // 创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置根包
        globalConfig.setBasePackage(GenCodeUtil.class.getPackage().getName());
        globalConfig.setAuthor("wjj-phoenix");
        globalConfig.setSince(LocalDateTime.now().toString());

        // 设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setTableDefGenerateEnable(true);
        globalConfig.enableEntity().setWithLombok(true);
        globalConfig.setEntityWithLombok(true);
        // 设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(21);
        globalConfig.getEntityConfig().setOverwriteEnable(true);
        globalConfig.getTableDefConfig().setOverwriteEnable(true);

        // 设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getMapperConfig().setMapperAnnotation(true);
        globalConfig.setMapperXmlGenerateEnable(true);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceClassPrefix("I");
        globalConfig.getStrategyConfig().setGenerateTables(Set.of("machine","resource_auth"));

        globalConfig.setServiceImplGenerateEnable(true);

        globalConfig.setControllerGenerateEnable(true);

        return globalConfig;
    }
}
