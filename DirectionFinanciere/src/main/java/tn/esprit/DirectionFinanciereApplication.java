package tn.esprit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DirectionFinanciereApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectionFinanciereApplication.class, args);
    }


}
