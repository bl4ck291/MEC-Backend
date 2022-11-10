package com.sante.store;

import com.sante.store.entities.Role;
import com.sante.store.entities.User;
import com.sante.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_CUSTOMER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "john@sante.com", "1234", "John", "Travolta", "California", "+71", new ArrayList<>()));
			userService.saveUser(new User(null, "will@sante.com", "1234", "Will", "Smith", "California", "+72", new ArrayList<>()));
			userService.saveUser(new User(null, "jim@sante.com", "1234", "Jim", "Carry", "California", "+73", new ArrayList<>()));
			userService.saveUser(new User(null, "arnold@sante.com", "1234", "Arnold", "Schwarzenegger", "California", "+74", new ArrayList<>()));

			userService.addRoleToUser("john@sante.com", "ROLE_CUSTOMER");
			userService.addRoleToUser("john@sante.com", "ROLE_MANAGER");
			userService.addRoleToUser("will@sante.com", "ROLE_MANAGER");
			userService.addRoleToUser("jim@sante.com", "ROLE_ADMIN");
			userService.addRoleToUser("arnold@sante.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold@sante.com", "ROLE_ADMIN");
			userService.addRoleToUser("arnold@sante.com", "ROLE_CUSTOMER");

		};
	}

}
