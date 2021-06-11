package com.pbteach.apollo.accountservice;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 1
@EnableApolloConfig // 2.集成Apollo配置中心
public class AccoutApplication {
    public static void main(String[] args) {
        // 3.要配置VM Options中的运行时参数 运行时参数要多配置一个-Dapollo.cacheDir=xx以缓存配置
        /**问题1：Apollo启动后会占用8080端口，应用需要重新server.port指定端口
         * 问题2：启动时，如果应用使用了Apollo中没有配置过的属性，会报错：
         *       Could not resolve placeholder 'sms.enable' in value "${sms.enable}"
         *       需要在Apollo中添加配置，或者应用不使用这个配置；
         *       如果Apollo中配置的值与应用中
         *       接收该配置值的类型不兼容也会报错，需要作出相应修改，比如Boolean无法接受String类型的配置值
         * 问题3：写application.properties/yml，配置namespace时一定要注意
         *       apollo.bootstrap.namespaces 和 apollo.bootstrap.namespace是不同的两个配置，
         *       前者可以配置多个namespace用逗号分隔，后者只能使用1个namespace，之后的都识别不到。
         * 问题4：Apollo配置的存储需要用到数据库，应用读取了Apollo中关于DataSource的配置之后
         *       应用中需要有实际的 mysql数据库连接依赖 以及 数据源依赖 才可以创建数据源-
         *       1.Cannot load driver class: com.mysql.cj.jdbc.Driver
         *       2.Failed to bind properties under 'spring.datasource.type' to java.lang.Class<javax.sql.DataSource>:
         * 问题5：视频中 缺少：mysql数据库连接依赖的导入，druid数据源依赖的导入；
         *       视频中 公用配置为 micro_service.spring-boot-http，我自己用的是 common_group.spring-boot-http
         */
        SpringApplication.run(AccoutApplication.class);
    }
}
