package tn.esprit.wala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class WebserviceWalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceWalaApplication.class, args);
    }

}
