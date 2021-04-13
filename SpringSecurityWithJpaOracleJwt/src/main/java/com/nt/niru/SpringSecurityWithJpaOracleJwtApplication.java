package com.nt.niru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nt.niru.repo.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses =UserRepository.class)
public class SpringSecurityWithJpaOracleJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJpaOracleJwtApplication.class, args);
	}

}
