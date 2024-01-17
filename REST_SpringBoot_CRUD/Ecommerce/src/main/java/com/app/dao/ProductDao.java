package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Category;
import com.app.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	//derived method
	List<Product> findByCategory(Category category);
	
	
	//try some JPQL here


}
