package com.velocity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.velocity.entity.Product;
import com.velocity.repository.ProductRepository;

@SpringBootApplication
public class ApaheVelocityImplementationDemoApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApaheVelocityImplementationDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product product = new Product(1, "name", "desc1");
		Product product1 = new Product(2, "name2", "desc2");
		Product product2 = new Product(3, "name 3 db", "desc3");
		Product product3 = new Product(4, "name 4 db", "desc4");
		productRepository.save(product);
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
	}

}
