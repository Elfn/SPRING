package com.pm.demo;

import com.pm.demo.dto.ProductDTO;
import com.pm.demo.entities.Product;
import com.pm.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		ProductDTO product = new ProductDTO();

		product.setDescription("wwwwww");

		productService.saveProduct(product);

	}
}
