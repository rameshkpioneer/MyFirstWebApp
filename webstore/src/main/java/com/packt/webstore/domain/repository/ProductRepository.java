 package com.packt.webstore.domain.repository;  
 import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product; 

public interface ProductRepository {  
        	
			//It will Return All the products in webstore
			List <Product> getAllProducts();
			
			//Update products in inventory
        	void updateStock(String productId, long noOfUnits);
        	
        	//Listing  products by category.
        	List<Product> getProductsByCategory(String category);
        	
        	//Listing based on filters by using Matrix params
        	List<Product> getProductsByFilter(Map<String,List<String>> filterParams);
        	
        	//Get product by Id
        	Product getProductById(String productID);
        	
        	//With Three types of varaibles.
        	List<Product> getProductByMultipleVariable(String category,Map<String,List<String>> filterParams, String pbrand );

        	//Add Product 
        	void addProduct(Product product);
        	
}

