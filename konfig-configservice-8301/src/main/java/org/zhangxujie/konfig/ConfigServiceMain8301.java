/**
 * FileName: ConfigServiceMain8301
 * Author:   jason
 * Date:     2021/4/4 15:15
 * Description:
 */
package org.zhangxujie.konfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigServiceMain8301 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceMain8301.class, args);
    }

}
