package umc.aehopark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AehoparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AehoparkApplication.class, args);
	}

}
