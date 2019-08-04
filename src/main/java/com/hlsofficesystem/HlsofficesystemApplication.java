package com.hlsofficesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.officesystem.mapper")
public class HlsofficesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlsofficesystemApplication.class, args);
	}

}
