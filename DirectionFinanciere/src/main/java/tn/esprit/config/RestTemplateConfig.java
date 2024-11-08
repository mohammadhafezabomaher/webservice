package tn.esprit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Define a RestTemplate bean that can be autowired in other classes
    @Bean
    public RestTemplate restTemplate() {
        //etudiant id=1,get etudiant in data base after etudiant.conatctid get from node
        return new RestTemplate();
    }
}