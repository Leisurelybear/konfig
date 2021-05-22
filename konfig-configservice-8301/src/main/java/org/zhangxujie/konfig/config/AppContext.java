/**
 * FileName: AppContext
 * Author:   jason
 * Date:     2021/5/20 11:54
 * Description:
 */
package org.zhangxujie.konfig.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppContext {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
