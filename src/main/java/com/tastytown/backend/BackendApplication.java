package com.tastytown.backend;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tastytown.backend.constants.Role;
import com.tastytown.backend.entity.User;
import com.tastytown.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication {
	private final UserRepository repository;
	private final PasswordEncoder encoder;
	@Value("${upload.file.dir}") // spring expression language
	private String FILE_DIR;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			var file = new File(FILE_DIR);
			if (!file.exists()) {
				file.mkdir();
				System.out.println("Folder Created to store food Images.");
			}
			System.out.println("Folder Already Exists.");
			// just 5mint left in the video watch it all there, nothing missed.
			// default admin creation
			String defaultAdminEmail = "admin@gmail.com";
			String defaultAdminPassword = "Deb";
			var existingAdmin = repository.findByUserEmail(defaultAdminEmail);

			if (!existingAdmin.isPresent()) {
				var adminUser = User.builder()
						.userEmail(defaultAdminEmail)
						.userPassword(encoder.encode(defaultAdminPassword))
						.role(Role.ROLE_ADMIN)
						.build();

				repository.save(adminUser);
				System.out.println("Default admin created: " + defaultAdminEmail + " / " + defaultAdminPassword);
			} else {
				System.out.println("Default admin already exists: " + defaultAdminEmail + " / " + defaultAdminPassword);
			}
		};

	}

}
