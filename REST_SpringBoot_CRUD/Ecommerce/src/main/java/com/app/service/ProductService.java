package com.app.service;

import java.util.List;

import com.app.entities.Category;
import com.app.entities.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product addNewProduct(Product transientProduct);
	
	Product getProductsById(Long productId);

	List<Product> getProductsByCategory(Category category);

	Product updateProduct(Product detachedProduct);

	String deleteProductById(Long productId);
}
