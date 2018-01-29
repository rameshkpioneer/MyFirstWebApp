package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl  implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void updateAllStock() {
		// TODO Auto-generated method stub
		List<Product> allProducts = productRepository.getAllProducts();
		
		for(Product p : allProducts){
			if(p.getUnitsInStock() < 500){
				productRepository.updateStock(p.getProductId(), p.getUnitsInStock()+1000);			}
		}
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.getAllProducts();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.getProductsByCategory(category);
	}

	
	public List<Product> getProductsByFilter(Map<String,
			List<String>> filterParams) {
			return productRepository.getProductsByFilter(filterParams);
			}
	
	@Override
	public Product getProductById(String productID) {
	return productRepository.getProductById(productID);
	}

	@Override
	public List<Product> getProductByMultipleVariable(String category, Map<String, List<String>> filterParams,
			String pbrand) {
		// TODO Auto-generated method stub
		return productRepository.getProductByMultipleVariable(category, filterParams, pbrand);
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.addProduct(product);
	}
	
	
	
}
