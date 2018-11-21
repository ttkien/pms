package password_management_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PMSServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(PMSServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PMSServiceApplication.class);
	}

}
