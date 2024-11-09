package tn.esprit.molka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = {"entities"})
public class MolkaJlassiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MolkaJlassiApplication.class, args);
	}

}
