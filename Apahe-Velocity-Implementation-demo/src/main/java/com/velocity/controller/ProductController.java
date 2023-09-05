package com.velocity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.velocity.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/template")
	public String getTemplate() {
		productService.exportVelocityTemplate();
		return "Template see in console";
	}
	
	@GetMapping("/generateHtml")
	public String writeToHtml() {
		productService.exportVelocityTemplateInHtml();
		return "HTML file generated";
	}
	
	@GetMapping("/generateJson")
	public String writeInJson() {
		productService.jsonForm();
		return "JSON file generated";
	}
	
	@GetMapping("/to")
	private String velocityToConsole() {
		String htmlString = productService.velocityToHtml();
		return htmlString;
	}
	
}
