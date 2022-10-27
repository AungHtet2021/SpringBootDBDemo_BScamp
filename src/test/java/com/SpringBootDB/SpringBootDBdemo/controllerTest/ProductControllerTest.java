package com.SpringBootDB.SpringBootDBdemo.controllerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import com.SpringBootDB.SpringBootDBdemo.Service.ProductService;
import com.SpringBootDB.SpringBootDBdemo.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest

public class ProductControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	List<Product>products=new ArrayList<Product>();
	Product product1=new Product();
	Product product2=new Product();
	
	@BeforeEach
	void setup() {
		product1.setId(1);
		product1.setPname("coffee");
		product1.setQuantity(500);
		product1.setUnit_price(400);
		
		product2.setId(2);
		product2.setPname("noodle");
		product2.setQuantity(500);
		product2.setUnit_price(100);
		
		products.add(product1);
		products.add(product2);
	}
	
	@DisplayName("test for fetching all products in controller")
	@Test
	public void givenToGetAllProduct_thenReturnListAllProduct()throws Exception {
		given(productService.getProducts()).willReturn(products);
		ResultActions response=mockMvc.perform(get("/product/get/products"));
		
		response
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()",is(products.size())))  // actual and expective
		.andExpect(jsonPath("$[0].id",is(product1.getId())));
	}
	
	
	@DisplayName("test for fetching product by ID in controller")
	@Test
	public void givenToGetAllProduct_sendID_thenReturnWithID()throws Exception {
		given(productService.getProduct(product1.getId())).willReturn(product1);
		ResultActions response=mockMvc.perform(get("/product/get/product/{id}",product1.getId()));
		
		response
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id",is(product1.getId())))  // actual and expective
		.andExpect(jsonPath("$.pname",is(product1.getPname())));
	}

		@DisplayName("Test for saving product in controller")
		@Test
		public void givenSaveProduct_sendProductData_thenReturnProductObject()throws Exception {
			given(productService.save(any(Product.class)))
			.willAnswer((invocation)->invocation.getArgument(0));
			
			
			ResultActions response=mockMvc.perform(
					post("/product/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(product1)));
			
			response
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id",is(product1.getId())))  // expective & actual 
			.andExpect(jsonPath("$.pname",is(product1.getPname())));
		}
		
		@DisplayName("Test for update product in controller")
		@Test
		public void givenUpdateProduct_sendUpdateData_thenReturnUpdateProductObject()throws Exception {
			given(productService.getProduct(product1.getId())).willReturn(product1);
			given(productService.updateProduct(any(Product.class))).willReturn(product2)
			.willAnswer((invocation)->invocation.getArgument(0));

			ResultActions response=mockMvc.perform(
					put("/product/update/{id}",product1.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(product1)));			
			response
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id",is(product2.getId())))  // actual and expective
			.andExpect(jsonPath("$.pname",is(product2.getPname())));
		}
		
		@DisplayName("Test for Delete product in controller")
		@Test
		public void givenDeleteId_whenDeleteProduct_thenReturn200()throws Exception {
//		    doNothing().when(productService).deleteProduct(product1.getId());
//	        willDoNothing().given(productService).deleteProduct(product1.getId());
	        given(productService.getProduct(product1.getId())).willReturn(product1);
	        ResultActions response = mockMvc.perform(delete("/product/delete/product/{id}", product1.getId()));
	        response.andExpect(status().isOk());
	     }
	
}




