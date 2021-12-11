package cn.edu.scu.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author TZQ
 */
@SpringBootConfiguration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
