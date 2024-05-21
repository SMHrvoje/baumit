package baumit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BaumitApplication {


	public static void main(String[] args) {
		SpringApplication.run(BaumitApplication.class, args);


	}

	@Bean
	public CommandLineRunner runAtStart() {
		return args -> {
			System.out.println("command line runner");
		};
	}

}
