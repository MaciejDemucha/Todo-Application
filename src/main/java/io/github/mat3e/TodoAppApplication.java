package io.github.mat3e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.Validator;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	//odpowiada za walidację tasków (otrzymujemy error 400 bad request zamiast 500 internal server)
	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}
