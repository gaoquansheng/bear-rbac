package com.bear.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bear.rbac.mapper")
@SpringBootApplication
public class RbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);
	}

}
