package com.packt.webstore.service;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductService {
		void updateAllStock();
		
		//It will Return All the products in webstore
		List <Product> getAllProducts();
		
		//List all products by category
		List<Product> getProductsByCategory(String category);
		
		List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
		
		Product getProductById(String productID);
		
		public List<Product> getProductByMultipleVariable(String category, Map<String, List<String>> filterParams, String pbrand);
		
		public void addProduct(Product Product); 
}
