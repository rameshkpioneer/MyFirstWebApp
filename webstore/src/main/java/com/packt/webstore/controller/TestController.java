package com.packt.webstore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {
	@RequestMapping("/test")
	public String welcome(Model model) {
		System.out.println(" Testing  in home controller");
	model.addAttribute("greeting", "Welcome to Web Store!"); 
	model.addAttribute("tagline", "The one and only amazing web store");    
	return "welcome"; 
	}

}
