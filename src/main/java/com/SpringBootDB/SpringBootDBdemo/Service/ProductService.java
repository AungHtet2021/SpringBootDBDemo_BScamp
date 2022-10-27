package com.SpringBootDB.SpringBootDBdemo.Service;

import java.util.List;

import com.SpringBootDB.SpringBootDBdemo.entity.Product;

public interface ProductService {
//CRUD
	
	public Product save(Product product);
	public List<Product> getProducts();
	public Product getProduct(int id);
	public Product updateProduct(Product product);
	public Product deleteProduct(int id);
	public List<Product> getProductwithQty(int qty);
}
