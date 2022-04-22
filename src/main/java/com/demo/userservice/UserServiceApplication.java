package com.demo.userservice;


import com.demo.userservice.domain.Repository;
import com.demo.userservice.domain.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserServiceApplication {

	static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(UserServiceApplication.class, args);

		
		Repository repository = applicationContext.getBean(Repository.class);

		User user = new User();
		user.setName("mong");
		user.setEmail("momo@sk.com");

		repository.save(user);
		
		System.out.println(user.getName() +" :  "+ user.getEmail());


	}

}
