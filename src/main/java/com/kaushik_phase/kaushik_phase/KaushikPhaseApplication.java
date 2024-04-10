package com.kaushik_phase.kaushik_phase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@ComponentScan
public class KaushikPhaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaushikPhaseApplication.class, args);
	}

}
