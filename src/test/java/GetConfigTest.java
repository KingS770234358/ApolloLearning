import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.junit.Test;

public class GetConfigTest {

    @Test
    public void readClusterConfigTest() throws InterruptedException {
        // 在VM options中 添加参数 -Dapollo.cluster = xxx 指定从哪个集群中读取数据
        Config appConfig = ConfigService.getConfig("micro_service.spring-boot-http");
        String configKey1 = "server.servlet.context-path";   // 公共中被个性化的配置
        String configKey2 = "server.tomcat.remote_ip_header";// 公共中的配置
        String value1 = null;
        String value2 = null;
        while (true){
            Thread.sleep(2000);
            // 使用Apollo Portal管理系统的text直接输入key=value的方式配置属性时，
            // 一定要注意，行首不能有空格！！！
            value1 = appConfig.getProperty(configKey1, null); // 一定要发布了才能读取到！！
            System.out.println(value1);
            value2 = appConfig.getProperty(configKey2, null); // 一定要发布了才能读取到！！
            System.out.println(value2);
        }
    }

    @Test
    public void commonNamespaceCustomizeReadConfigTest() throws InterruptedException {
        // 读取应用默认的namespace
        // Config appConfig = ConfigService.getAppConfig();
        //                             读取指定的 关联的公共配置的 namespace，namespace在生成名字的时候会加上部门名来提高唯一性
        //                                          在VM options中已经设置确定了AppId
        Config appConfig = ConfigService.getConfig("micro_service.spring-boot-http");
        String configKey1 = "server.servlet.context-path";   // 公共中被个性化的配置
        String configKey2 = "server.tomcat.remote_ip_header";// 公共中的配置
        String value1 = null;
        String value2 = null;
        while (true){
            Thread.sleep(2000);
            // 使用Apollo Portal管理系统的text直接输入key=value的方式配置属性时，
            // 一定要注意，行首不能有空格！！！
            value1 = appConfig.getProperty(configKey1, null); // 一定要发布了才能读取到！！
            System.out.println(value1);
            value2 = appConfig.getProperty(configKey2, null); // 一定要发布了才能读取到！！
            System.out.println(value2);
        }
    }

    @Test
    public void namespaceReadConfigTest() throws InterruptedException {
        // 读取应用默认的namespace
        // Config appConfig = ConfigService.getAppConfig();
        // 读取指定的namespace
        Config appConfig = ConfigService.getConfig("spring-rocketmq");
        String configKey = "rocketmq.name-server";
        String value = null;
        while (true){
            Thread.sleep(2000);
            value = appConfig.getProperty(configKey, null); // 一定要发布了才能读取到！！
            System.out.println(value);
        }
    }
    @Test
    public void firstTest(){
        // VM options:
        // -Dapp.id=apollo-quickstart -Denv=DEV -Ddev_meta=http://localhost:8080
        // 需要通过运行时参数VM options来指定 配置中心的IP:PORT、从哪个项目 的 哪个环境 下获取配置
        Config appConfig = ConfigService.getAppConfig();
        // 获取配置信息，第一个参数：配置的Key，第二个参数：key没有对应配置情况下的缺省值
        String configKey = "sms.enable";
        String value = appConfig.getProperty(configKey, null);
        System.out.println(value);
    }
    @Test
    public void hotPublishTest() throws InterruptedException {
        // 测试Apollo的热发布特性，应用程序不停地从Apollo服务器中读取配置参数值
        // 对Apollo服务端配置参数的修改会实时地体现在应用程序中
        Config appConfig = ConfigService.getAppConfig();
        String configKey = "sms.enable";
        String value = null;
        while (true){
            Thread.sleep(2000);
            value = appConfig.getProperty(configKey, null);
            System.out.println(value);
        }
    }
    @Test
    public void AccountServiceTest() throws InterruptedException {
        Config appConfig = ConfigService.getAppConfig();
        String configKey = "sms.enable";
        String value = null;
        while (true){
            Thread.sleep(2000);
            value = appConfig.getProperty(configKey, null);
            System.out.println(value);
        }
    }
}
