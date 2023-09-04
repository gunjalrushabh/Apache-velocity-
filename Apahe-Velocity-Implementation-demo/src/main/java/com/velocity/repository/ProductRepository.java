package com.velocity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.velocity.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
