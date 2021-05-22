package org.zhangxujie.konfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigAuthMain8021 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAuthMain8021.class, args);
    }

}
