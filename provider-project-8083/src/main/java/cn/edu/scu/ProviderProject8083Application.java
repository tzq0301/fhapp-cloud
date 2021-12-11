package cn.edu.scu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderProject8083Application {

    public static void main(String[] args) {
        SpringApplication.run(ProviderProject8083Application.class, args);
    }

}
