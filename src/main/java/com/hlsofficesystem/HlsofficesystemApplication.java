package com.hlsofficesystem;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication
@MapperScan("com.hlsofficesystem.mapper")
public class HlsofficesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlsofficesystemApplication.class, args);
	}


}
