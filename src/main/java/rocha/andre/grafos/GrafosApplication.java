package rocha.andre.grafos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rocha.andre.grafos.service.MainService;

@SpringBootApplication
public class GrafosApplication implements CommandLineRunner {

	@Autowired
	private MainService mainService;

	public static void main(String[] args) {
		SpringApplication.run(GrafosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainService.runMain();
	}
}
