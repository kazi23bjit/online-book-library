package com.bjitacademy.booklibrary.onlinebooklibrary;

import com.bjitacademy.booklibrary.onlinebooklibrary.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookLibraryApplication {
	@Bean
	CommandLineRunner run(RoleService roleService){
		return args -> {
			roleService.addRole("CUSTOMER");
			roleService.addRole("ADMIN");
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(OnlineBookLibraryApplication.class, args);
	}

}
