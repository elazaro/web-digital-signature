package com.mxrisc.auth_api;

import com.mxrisc.auth_api.config.SignerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties(SignerProperties.class)
public class AuthApiApplication {
   private static final Logger log = LoggerFactory.getLogger("com.mxrisc");

	public static void main(String[] args) {
      log.info("Starting digital signature application");
		SpringApplication.run(AuthApiApplication.class, args);
	}

}
