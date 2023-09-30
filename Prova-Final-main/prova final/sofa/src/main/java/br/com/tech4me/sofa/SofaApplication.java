package br.com.tech4me.sofa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SofaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SofaApplication.class, args);
	}

}
