package com.example.historyPlayerComposite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class HistoryPlayerCompositeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryPlayerCompositeApplication.class, args);
	}
}
