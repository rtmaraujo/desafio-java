package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "br.com.desafio")
@EnableConfigurationProperties
public class DesafioApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

}


