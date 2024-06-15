package com.thembelanimkhize.Mail_send2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MailSend2Application {

	public static void main(String[] args) {
		SpringApplication.run(MailSend2Application.class, args);
	}

}
