package fr.olprog_b.food_buddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableJpaAuditing
@SpringBootApplication
@EnableMethodSecurity
public class FoodBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodBuddyApplication.class, args);
	}

}
