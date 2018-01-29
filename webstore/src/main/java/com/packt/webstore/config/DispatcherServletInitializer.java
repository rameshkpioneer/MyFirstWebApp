package com.packt.webstore.config;

import org.springframework.web.servlet.support .AbstractAnnotationConfigDispatcherServletInitializer; 

 
public class DispatcherServletInitializer extends       AbstractAnnotationConfigDispatcherServletInitializer {  
	@Override           
	protected Class<?>[] getRootConfigClasses() {
		 return new Class[] { RootApplicationContextConfig.class 
		};
	} 
	@Override
	protected Class<?>[] getServletConfigClasses() {  
		System.out.println("Dis Config2");

	return new Class[] {    
			WebApplicationContextConfig.class 
     };   
	}      
	@Override  
	protected String[] getServletMappings() { 
		System.out.println("Dis Config3");

		return new String[] { "/" };  
		} 
	} 