package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.ProductDao;
import com.app.entities.Category;
import com.app.entities.Product;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	//dependency
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}	//inherited method - findAll

	@Override
	public Product addNewProduct(Product transientProduct) {
		// CrudRepository method : T save (T entity)
		return productDao.save(transientProduct);
	}	//inherited method - save

	@Override
	public Product getProductsById(Long productId) {
		return productDao.findById(productId).orElseThrow(
				()->new ResourceNotFoundException("You have entered Invalid Product Id"));
	} //inherited method - findById

	@Override
	public List<Product> getProductsByCategory(Category category) {
		//		This creates additional overhead
		//		return productDao.findAll().stream()
		//				.filter(x->x.getCategory() == category).collect(Collectors.toList());
		return productDao.findByCategory(category);
	}

	@Override
	public Product updateProduct(Product detachedProduct) {
		if(productDao.existsById(detachedProduct.getId())){
			//exists --update
			return productDao.save(detachedProduct);
		}
		throw new ResourceNotFoundException("Invalid product id !!!!");
	}//tx.commit() --> update query

	@Override
	public String deleteProductById(Long productId) {
		if(productDao.existsById(productId)) {
			productDao.deleteById(productId);
			return "deleted product details...";
		}
		return "deletion of product details failed !!!!!";
	}
	
}
