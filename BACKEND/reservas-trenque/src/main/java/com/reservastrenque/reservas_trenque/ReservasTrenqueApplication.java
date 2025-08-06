package com.reservastrenque.reservas_trenque;

import com.reservastrenque.reservas_trenque.users.usecase.InsertAdminUserUseCase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasTrenqueApplication {

	@Autowired
	private InsertAdminUserUseCase insertAdminUser;
	public static void main(String[] args) {
		SpringApplication.run(ReservasTrenqueApplication.class, args);
	}

	@PostConstruct
	public void init() {
		insertAdminUser.execute();
	}
}
