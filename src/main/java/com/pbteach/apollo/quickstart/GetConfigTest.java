package com.pbteach.apollo.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.time.LocalDateTime;

/**
 * @author www.pbteach.com
 * @version 1.0
 **/
public class GetConfigTest {
    /*public static void main(String[] args) {
//        Config appConfig = ConfigService.getAppConfig(); //读取默认namespace下的配置信息
        Config appConfig = ConfigService.getConfig("spring-rocketmq");//读取指定 namespace下的配置信息
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取配置信息,第一个参数：配置的key，第二个参数：默认值
            String value = appConfig.getProperty("rocketmq.name-server", null);
            System.out.printf("now: %s, rocketmq.name-server: %s%n", LocalDateTime.now().toString(), value);
        }


    }*/

    public static void main(String[] args) {
//        Config appConfig = ConfigService.getAppConfig(); //读取默认namespace下的配置信息
        Config appConfig = ConfigService.getConfig("micro_service.spring-boot-http");//读取指定 namespace下的配置信息
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取配置信息,第一个参数：配置的key，第二个参数：默认值
            String value = appConfig.getProperty("server.servlet.context-path", null);
            System.out.printf("now: %s, server.servlet.context-path: %s%n", LocalDateTime.now().toString(), value);
        }


    }
}
