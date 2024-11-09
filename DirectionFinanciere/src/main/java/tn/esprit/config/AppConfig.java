package tn.esprit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Define a RestTemplate bean that can be autowired in other classes
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}