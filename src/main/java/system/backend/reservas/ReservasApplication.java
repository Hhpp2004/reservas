package system.backend.reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservasApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		System.out.println("http://localhost:8080/usuario");
	}
}