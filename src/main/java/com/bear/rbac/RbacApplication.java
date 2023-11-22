package com.bear.rbac;

import com.bear.rbac.entity.SysUser;
import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@MapperScan("com.bear.rbac.mapper")
@SpringBootApplication
public class RbacApplication {

	@Autowired
	private RedisTemplate redisTemplate;

	@PostConstruct
	public void init() {
		SysUser sysUser = new SysUser();
		sysUser.setSex("nan");
		redisTemplate.opsForValue().set("name",sysUser);
		SysUser sysUser1 = (SysUser) redisTemplate.opsForValue().get("name");
		System.out.println(sysUser1.getSex());
	}
	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);


	}


}
