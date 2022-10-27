package com.SpringBootDB.SpringBootDBdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.SpringBootDB.SpringBootDBdemo.entity.Product;
@EnableJpaRepositories
@Repository
public interface ProductRepository extends JpaRepository <Product,Integer>{          
	@Query(value="select * from product where quantity>=:qty",nativeQuery=true)
	public List<Product> getOver100Qty(int qty);
}
