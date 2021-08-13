package br.com.wsb.DonJose;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class DonJoseApplication implements CommandLineRunner  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(DonJoseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
	}
	
}