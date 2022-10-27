package com.SpringBootDB.SpringBootDBdemo.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.SpringBootDB.SpringBootDBdemo.Service.ProductServiceImpl;
import com.SpringBootDB.SpringBootDBdemo.entity.Product;
import com.SpringBootDB.SpringBootDBdemo.repository.ProductRepository;
@ExtendWith(MockitoExtension.class)
public class ProductSeviceTest {
		@Mock  	// to created fake obj
		ProductRepository productRepository;
		
		@InjectMocks
		ProductServiceImpl productServiceImpl;
		
		Product product=new Product();
		Product product2=new Product();
		List<Product> products=new ArrayList<>();
		
		@BeforeEach
		private void setup() {
			product.setId(1);
			product.setPname("coffee");
			product.setQuantity(500);
			product.setUnit_price(400);
			product2.setId(2);
			product2.setPname("noodle");
			product2.setQuantity(500);
			product2.setUnit_price(100);
			products.add(product);
			products.add(product2);
		}
		
		
		@DisplayName("Test for save Product in service layer")
		@Test
		 void givenSaveProduct_sendProductData_thenReturnProjectObj() {
			given(productRepository.save(product)).willReturn(product);
			Product newProduct=productServiceImpl.save(product);
			assertThat(newProduct).isNotNull();	// assertThat return true or false
			
		}
		
		@DisplayName("Test for get All  Product in service layer")
		@Test
		 void givengetProduct_thenReturnProjectwithList() {
			given(productRepository.findAll()).willReturn(products);
			List<Product> products=productServiceImpl.getProducts();
			assertThat(products).isNotNull();
			assertThat(products).contains(product2);
			assertThat(products.size()).isEqualTo(2);
		}
		
		@DisplayName("Test for get Product by Id   in service layer")
		@Test
		 void givengetProduct_sendId_thenReturnProjectwithId() {
			given(productRepository.findById(1)).willReturn(Optional.of(product));
			Product productWithId=productServiceImpl.getProduct(1);
			assertThat(productWithId.getId()).isEqualTo(1);
			assertThat(productWithId.getPname()).isEqualTo(product.getPname());
		}
		
		@DisplayName("Test for delete Product in service layer")
		@Test
		 void givenDeleteProduct_sendId_thenNull() {
			willDoNothing().given(productRepository).deleteById(1);		
			productServiceImpl.deleteProduct(1);
			verify(productRepository, times(1)).deleteById(1);

		}
		
		
		@DisplayName("Test for updated Product in service layer")
		@Test
		 void givenUpdateProduct_sendupdate_thenReturnUpdateProduct() {
			given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
			given(productRepository.save(product)).willReturn(product2);
			Product updatedProduct=productServiceImpl.updateProduct(product);
			assertThat(updatedProduct.getPname()).isEqualTo(product2.getPname());
			assertThat(updatedProduct.getUnit_price()).isEqualTo(product2.getUnit_price());
			assertThat(updatedProduct.getQuantity()).isEqualTo(product2.getQuantity());
		}
		
		
		
}
