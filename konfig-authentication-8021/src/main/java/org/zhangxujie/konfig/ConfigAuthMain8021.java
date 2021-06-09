package org.zhangxujie.konfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigAuthMain8021 extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAuthMain8021.class, args);
    }

}
