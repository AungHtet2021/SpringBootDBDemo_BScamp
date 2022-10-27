package com.SpringBootDB.SpringBootDBdemo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootDB.SpringBootDBdemo.entity.Product;
import com.SpringBootDB.SpringBootDBdemo.repository.ProductRepository;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Override
	public Product save(Product product) {
		Product newProduct=productRepository.save(product);
		return newProduct;
	}
	@Override
	public List<Product> getProducts() {
		List<Product> products=productRepository.findAll()	;
		return products;
	}
	@Override
	public Product getProduct(int id) {
		Product product=productRepository.findById(id).orElse(null);
		return product;
	}
	@Override
	public Product updateProduct(Product product) {
		Product original=productRepository.findById(product.getId()).orElse(null);
		if(original!=null) {
			original.setPname(product.getPname());
			original.setQuantity(product.getQuantity());
			original.setUnit_price(product.getUnit_price());
			original=productRepository.save(original);
			
		}
		return original;
	}
	@Override
	public Product deleteProduct(int id) {
		productRepository.deleteById(id);
		return null;
	}
	@Override
	public List<Product> getProductwithQty(int qty) {
		// TODO Auto-generated method stub
		return productRepository.getOver100Qty(qty);
	}
	
	

}
