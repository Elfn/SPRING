package com.mocktest.demo;

import com.mocktest.demo.bootstrap.PersonBootstrap;
import com.mocktest.demo.classes.Departments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


		List<Object> personList = new ArrayList<>();

		PersonBootstrap bootstrap = PersonBootstrap.getInstance();

		bootstrap.addPerson(bootstrap.createPerson("Elimane", Departments.A,45));
		bootstrap.addPerson(bootstrap.createPerson("Sam", Departments.B,25));
		bootstrap.addPerson(bootstrap.createPerson("Yann", Departments.C,32));
		bootstrap.addPerson(bootstrap.createPerson("Ericks", Departments.A,28));

		personList.forEach(person -> {
			System.out.println(person);
		});
	}
}
