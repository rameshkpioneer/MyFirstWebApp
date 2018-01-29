package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HomeController { 
	
	
	@RequestMapping("/welcome")
	public String welcome(Model model) {
	//	System.out.println(" Testing  in home controller");
	model.addAttribute("greeting", "Welcome to Web Store!"); 
	model.addAttribute("tagline", "The one and only amazing web store"); 
	
	//return "redirect:/welcome/greeting";
	return "forward:/test/welcome/greeting";
	//return "welcome";
	}
	@RequestMapping("/welcome/greeting")
	public String greeting() {
	return "welcome";
	}
	
	
}
             