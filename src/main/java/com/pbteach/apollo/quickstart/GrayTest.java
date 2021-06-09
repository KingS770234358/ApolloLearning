package com.pbteach.apollo.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.time.LocalDateTime;

/**
 * @author www.pbteach.com
 * @version 1.0
 **/
public class GrayTest {
    // VM options:
    // -Dapp.id=apollo-quickstart -Denv=DEV -Ddev_meta=http://localhost:8080
    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigService.getAppConfig();
        String someKey = "timeout";

        while (true) {
            String value = config.getProperty(someKey, null);
            System.out.printf("now: %s, timeout: %s%n", LocalDateTime.now().toString(), value);
            Thread.sleep(3000L);
        }
    }
}
