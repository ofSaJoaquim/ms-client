package br.com.branetlogistica.msclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientApplication.class, args);
	}

}
