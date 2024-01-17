package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	public ProductController() {
		System.out.println("in def ctor of " + getClass());
	}
	
	//http://localhost:8080/products
	//Method : GET
	@GetMapping
	public List<Product> getProducts() {
		System.out.println("in get products");
		return productService.getAllProducts();
	}
	
	//http://localhost:8080/products
	//method : POST
	@PostMapping
	//@RequestBody should be from org.springframework.web.bind.annotation
	public Product addNewProduct(@RequestBody Product transientProduct) {
		System.out.println("in add new product " + transientProduct);
		return productService.addNewProduct(transientProduct);
	}
	
	//http://localhost:8080/products/{productId}
	//Method : GET
	@GetMapping("/{productId}") //template pattern
	public Product getProductsById(@PathVariable Long productId) {
		System.out.println("in get product by id " + productId);
		return productService.getProductsById(productId);
	}

	//	http://localhost:8080/products/category/{categoryName}
	//  category tp avoid ambiguity error with /products/{productId} above
	//	Method : GET
	//	Hint : List<Product> findByCategory(Category cat)
	@GetMapping("/category/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable Category categoryName){
		System.out.println("in get products by category " + categoryName);
		return productService.getProductsByCategory(categoryName);
	}	
	
	//	http://localhost:8080/products
	//	Method : PUT
	@PutMapping
	public Product updateProduct(@RequestBody Product detachedProduct) {
		System.out.println("in update product " + detachedProduct);
		return productService.updateProduct(detachedProduct);
	}

	//	http://localhost:8080/products/{productId}
	//	Method : DELETE
	@DeleteMapping("/{productId}")
	public String deleteProductById(@PathVariable Long productId)
	{
		System.out.println("in del product "+productId);
		return productService.deleteProductById(productId);
	}
	
}
