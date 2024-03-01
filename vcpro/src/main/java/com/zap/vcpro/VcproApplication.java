package com.zap.vcpro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VcproApplication {
	@Value("${zap_api_key}")
	private static  String ZAP_API_key;
	public static void main(String[] args) {
		System.out.println(">>>>"+  ZAP_API_key);
		SpringApplication.run(VcproApplication.class, args);
	}

}
