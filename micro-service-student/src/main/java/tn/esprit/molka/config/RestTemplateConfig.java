package tn.esprit.molka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        //etudiant id=1,get etudiant in data base after etudiant.conatctid get from node
        return new RestTemplate();
    }
}
