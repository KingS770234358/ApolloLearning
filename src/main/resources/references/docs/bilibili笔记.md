前文见官方QuickStart教程

apollo-portal是1个管理控制台，默认端口8070，账号：apollo，密码：admin

其中的配置对应数据库apolloconfigdb中的item表，



### 3.3代码实现

#### 3.3.1发布配置

1.新建项目

部门、AppId、应用负责人、项目管理员

2.右边：+新增配置

选择集群： 环境   集群

​			      DEV   default

3.未发布->点击发布 发布配置->配置生效（写好Comment）

####3.3.2Java编写代码读取配置（参考workspace中ApolloLearning项目）

1.新建maven项目，pom.xml文件中导入依赖。

Java编写的代码是作为  Apollo客户端 存在的，读取Apollo服务器上的数据；

因此Java编写的应用需要导入apollo-client的依赖



#### SpringBoot项目集成Apollo

1.构建SpringBoot项目

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- 项目坐标 -->
    <groupId>com.pbteach.apollo</groupId>
    <artifactId>apollo-quickstart</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!-- SpringBoot 项目继承父项目 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
    </parent>

    <!-- JDK版本 -->
    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- apollo客户端依赖，使该项目可以成为apollo的客户端，读取apollo服务器上的配置 -->
        <dependency>
            <groupId>com.ctrip.framework.apollo</groupId>
            <artifactId>apollo-client</artifactId>
            <version>1.1.0</version>
        </dependency>
        <!-- Apollo配置的存储需要用到数据库，应用读取了Apollo中关于DataSource的配置之后
             应用中需要有实际的数据源依赖 以及 mysql数据库连接依赖 才可以创建数据源-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.6</version>
        </dependency>
        <!-- SpringBoot 项目相关依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <!-- 使用其他的日志依赖，排除自带依赖，防止冲突 -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- 继承SpringMVC Web的依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- thmeleaf模板引擎 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- 日志组件的相关依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.28</version>
        </dependency>
        <!-- 单元测试的依赖 https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- 使用maven插件 -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```



#### 部署第二套环境

1.不同套的环境 受同一个PortalService管理端 只有1个ApolloPortalDB 8070

2.不同套的环境 要有不同套的 ConfigService（8080） 和 AdminService（8090）  不同套的ApolloConfigDB

  新环境的设置 ConfigService（8081） 和 AdminService（8091）  ApolloConfigDB_PRO

3.安装第二套生产环境的 ApolloConfigDB数据库

参考前一套环境的 apolloconfigdb.sql 文件，写一套 生产环境的apolloconfigdb-produce.sql：

修改要创建的数据库名；修改Use 数据库名！！！；修改ServerConfig表中euraka.service.url为8081端口；

运行这个.sql文件，创建第二套环境的数据库

4.编写脚本

D:\Apollo\Apollo-1.8.2\configandadminservice-produceenv.sh

其中主要是要修改连接的数据库到3中新创建的数据库 和 使用的端口 8081 8091

5.在管理系统的系统参数配置中， apollo.portal.envs中要加入新的pro

6.重启portal，启动时要指定 前述两套环境的meta server的地址  写成portal-produceenv.sh脚本

一个是8080 一个是8081，不要写错了！

**************************************************************************************************************************************************************

-Ddev_meta=http://localhost:8080/ -Dpro_meta=http://localhost:8081/ -Dserver"."port=8070

这里XXX_meta的 XXX就是环境名，贯穿整个使用，包括在IDEA中的配置，在Portal页面中的配置

*********************************************************************************************************************************************************************************************************************************************************************

Portal管理系统中， apollo.portal.envs  根据 xxx_meta 的 xxx 进行添加

7.重新登录Portal管理系统，系统提示补全环境。需要点击【左下角】的补全环境，然后在补全环境中缺失的namespace，这里 不会补全从公共namespace继承来的namespace，要手动关联。

同时要注意的是：

比如有两个项目 project1     project2

project1 下 有1套环境，project2只有1套环境。

project2中发布了公共的namespace ， 并且被project1的环境 关联。

此时，project1创建第2套新环境，在default集群中补全namespace，此时不会补全project2发布的公共namespace。

=====>需要在project2中补全和project1中同样的新环境，然后在project2中，把公共namespace先同步到project2的新环境中（一定要记得发布！）；此时回到project1，再关联project2中的公共namespace才完全生效。

8.通过同步的方式把 已有环境的namespace下的配置 同步 到新环境的 **默认集群** 的namespace下

============================================

9.使用SpringBoot项目读取配置，首先要在resource下的apollo-env.properties配置文件中新增server_meta的配置

```properties
pro.meta=http://localhost:8081
```

通过切换 VM Options中的 -Denv参数来切换环境，读取不同环境中的配置。

同时  可以删去VM Options中的 -Dapollo.cluster参数，切换到 默认集群下。

我们 同一个集群（默认集群）的同一个namespace下查看同一个配置

常用的要检查的端口：8080 8090 8081 8091 8070（portal）

#### 灰度发布
灰度 创建灰度 新增灰度规则（加入要更新的实例 客户端ip），可以 转换成全量发布，或者放弃灰度，
还可以修改灰度版本里的配置（可以产生更改历史）灰度版本可以视为主版本一个层级，只是其中以这配置，
只对指定的实例（客户端ip）生效。