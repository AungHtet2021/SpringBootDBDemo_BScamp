package com.SpringBootDB.SpringBootDBdemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootDB.SpringBootDBdemo.Service.ProductService;
import com.SpringBootDB.SpringBootDBdemo.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value="/save",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product newProduct = productService.save(product);
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/get/products")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> product=productService.getProducts();
		return new ResponseEntity<List<Product>>(product,HttpStatus.OK);
		
	}  
	
	@GetMapping(value="/get/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id")int id){
		Product product=productService.getProduct(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
		
	}   
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<Product> putProduct(@RequestBody Product product)  {                          
		Product update_product=productService.updateProduct(product);
		return new ResponseEntity<Product>(update_product,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id)  {                          
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@GetMapping(value="/get/productqty/{qty}")
	public ResponseEntity<List<Product>> getProductQty(@PathVariable("qty") int qty){
		List<Product> product=productService.getProductwithQty(qty);
		return new ResponseEntity<List<Product>>(product,HttpStatus.OK);
		
	} 
	
}








