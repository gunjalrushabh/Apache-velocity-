package com.velocity.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.velocity.entity.Product;
import com.velocity.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	
    public void exportVelocityTemplate() {
		
//    	VelocityEngine velocityEngine = new VelocityEngine();
//    	Map<String, Object> model = new HashMap<>();
//    	Product product = productRepository.findById(id).get();
//    	List<Product>productList = productRepository.findAll();
//    	model.put("product", product);
//    	model.put("productList", productList);
//    	
    	//String templateText = VelocityEngine
    	
//    	Velocity.init();
//    	VelocityContext velocityContext = new VelocityContext();
    	Product product = new Product(1, "Sample Product", "This is a sample product description");

//        List<Product> productList = new ArrayList<>();
//        productList.add(new Product(2, "Product 2", "Description for Product 2"));
//        productList.add(new Product(3, "Product 3", "Description for Product 3"));
        
    	List<Product> productList = productRepository.findAll();
    	
    	// this is for getting for getting .vm file present in src/main/resource/templates/email_template.vm
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);       
        VelocityContext context = new VelocityContext();           
        Template template = Velocity.getTemplate("templates/email_template.vm");
      
        //in context we are putting key = present in vm file
        context.put("product", product);
        context.put("productList", productList);
        
        //writing into console
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer.toString());
  
    }
    
  public void exportVelocityTemplateInHtml() {

    	Product product = new Product(1, "Sample Product", "This is a sample product description");
     
    	List<Product> productList = productRepository.findAll();
    	
    	// this is for getting for getting .vm file present in src/main/resource/templates/email_template.vm
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init( p );       
        VelocityContext context = new VelocityContext();           
        Template template = Velocity.getTemplate("templates/email_template.vm");
      
        //in context we are putting key = present in vm file
        context.put("product", product);
        context.put("productList", productList);
        
        
         try {
			FileWriter writer = new FileWriter(new File("src/main/resources/template3.html"));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    }
  
  public void jsonForm() {
	  Product pobj = productRepository.findById(4).get();
	  
	  Velocity.init();
	  VelocityContext velocityContext = new VelocityContext();
	  velocityContext.put("id", pobj.getId());
	  velocityContext.put("name", pobj.getName());
	  velocityContext.put("description", pobj.getDescription());
	  
	  
	  //json to ToString()
	  Template template = Velocity.getTemplate("templates/jsonvm.vm");
	  StringWriter writer = new StringWriter();
	  template.merge(velocityContext, writer);
	  System.out.println(writer.toString());
	  
	 
	try {
		 FileWriter writerjson = new FileWriter(new File("src/main/resources/templatevelocity.json"));
		 template.merge(velocityContext, writerjson);
		 writerjson.flush();
		 writerjson.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
  }
  
  public String velocityToHtml() {
	  
	  Product pobj = new Product(100, "name", "description");
	  List<Product> list =  new ArrayList<>();
	
	  Product p = new Product(200, "name - 200", "description - 200");
	  Product p1 = new Product(300, "name - 300", "description - 300");
	  list.add(p);
	  list.add(p1);
	  //List<Product> list = productRepository.findAll();
	  
	  Properties prop = new Properties();
      prop.setProperty("resource.loader", "class");
      prop.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	  
      Velocity.init(prop);
	  VelocityContext velocityContext = new VelocityContext();
	  velocityContext.put("product", pobj);
	  velocityContext.put("productList", list);
	  
	  Template template = Velocity.getTemplate("templates/email_template.vm");
	//template.merge(velocityContext, writer)  // we need here writer this writer can get from 
	//StringWriter to display on console 
	//FileWriter to export html file to destination path
	  StringWriter writer = new StringWriter();
	  template.merge(velocityContext, writer);
	  System.out.println(writer.toString());
	  
	  return writer.toString();
	
//	try {
//		 FileWriter writer1 = new FileWriter(new File("src/main/resources/newhtml.html"));
//		 template.merge(velocityContext, writer1);
//		 writer1.flush();
//		 writer1.close();
//		 
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
  
  }
}

