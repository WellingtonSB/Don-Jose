package br.com.wsb.DonJose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DonJoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonJoseApplication.class, args);
	}

}
