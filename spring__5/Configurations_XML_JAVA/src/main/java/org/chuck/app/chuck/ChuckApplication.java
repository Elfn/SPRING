package org.chuck.app.chuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:chuck-config.xml")
public class ChuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChuckApplication.class, args);
	}
}
