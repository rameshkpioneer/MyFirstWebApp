package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Product;
import com.packt.webstore.service.CustomerService;

@Controller
public class CustomerController {

@Autowired	
 private CustomerService customerService;


@RequestMapping("/customers")
public  String customerList(Model model){
	 model.addAttribute("customers", customerService.getAllCustomers());
	 return "customer";
}

			@RequestMapping(value = "/customers/add", method =RequestMethod.GET)
			public String getAddNewProductForm(Model model) {
			Customer newCustomer = new Customer();
			model.addAttribute("newCustomer", newCustomer);
			return "addCustomer";
			}
			
			
			@RequestMapping(value = "/customers/add", method =RequestMethod.POST)
			public String
			processAddNewProductForm(@ModelAttribute("newProduct")Customer newCustomer) {
				customerService.addCustomer(newCustomer);
			return "redirect:/customers";
			
			}










	
	
}
