package com.packt.webstore.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import   org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import com.packt.webstore.domain.Product;
import com.packt.webstore.interceptor.ProcessingTimeLogInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;

@Configuration
@EnableWebMvc  
@ComponentScan("com.packt.webstore")

public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

@Override
public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
configurer.enable();
}

@Bean 
public InternalResourceViewResolver    getInternalResourceViewResolver() { 
InternalResourceViewResolver resolver = new  InternalResourceViewResolver();
resolver.setViewClass(JstlView.class); 
resolver.setPrefix("/WEB-INF/views/");
resolver.setSuffix(".jsp"); 
return resolver;  
}

@Override
public void configurePathMatch(PathMatchConfigurer configurer) {
	UrlPathHelper urlPathHelper = new UrlPathHelper();
	urlPathHelper.setRemoveSemicolonContent(false);
	configurer.setUrlPathHelper(urlPathHelper);
}

/*** Externalizing messages
 * To make the web application ready for internationalization.
 * Adding all the messages from property file(External File)
 * @return
 */
@Bean
public MessageSource messageSource() {
ResourceBundleMessageSource resource = new
ResourceBundleMessageSource();
resource.setBasename("messages");
return resource;
}


/**
 * To add static resources to project
 */
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/img/**")
	.addResourceLocations("/resources/images/");
	/*registry.addResourceHandler("/pdf/**")
	.addResourceLocations("/resources/pdf/");*/
}
/**
 * To Upload File to Server to dynamically populate the 
 * Static Resouces.
 * @return
 */
@Bean
	public CommonsMultipartResolver multipartResolver() {
	CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	resolver.setDefaultEncoding("utf-8");
	resolver.setMaxUploadSize(10240000);
	return resolver;
}
//--Contetnegotiation 
/**
 * For JSON View
 * @return
 */

@Bean
public MappingJackson2JsonView jsonView() {
MappingJackson2JsonView jsonView = new
MappingJackson2JsonView();
jsonView.setPrettyPrint(true);
return jsonView;
}
/**
 * For XML View 
 * @return
 */

@Bean
public MarshallingView xmlView() {
Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
marshaller.setClassesToBeBound(Product.class);
MarshallingView xmlView = new MarshallingView(marshaller);
return xmlView;
}
/**
 * Here we are adding different kinds of views
 * to our contentnegotiatigviewResolver
 * 
 * @param manager
 * @return
 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		ArrayList<View> views = new ArrayList<>();
		views.add(jsonView());
		views.add(xmlView());
		resolver.setDefaultViews(views);
		return resolver;
	}

	/**
	 * To add interceptors , to perform some actions pre processing the request 
	 * and post processing the request. After completion
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new ProcessingTimeLogInterceptor());
	//Registering implicit Interceptor 
	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	localeChangeInterceptor.setParamName("language");
	registry.addInterceptor(localeChangeInterceptor);
	
	//Promo code interceptors
	registry.addInterceptor(promoCodeInterceptor()).addPathPatterns("/**/market/products/specialOffer");
	
	}
	
	/**
	 * To Achieve internationalization
	 */
	@Bean
	public LocaleResolver localeResolver(){
	SessionLocaleResolver resolver = new
	SessionLocaleResolver();
	resolver.setDefaultLocale(new Locale("en"));
	return resolver;
	}
	
	
	/***
	 * Mappping interceptors : Only  for specific request Spring MVC Need to executes 
	 * The logic of intercepters . The  following method will help 
	 * you to do .
	 * @return
	 */
	@Bean
	public HandlerInterceptor promoCodeInterceptor() {
	PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
	promoCodeInterceptor.setPromoCode("OFF3R");
	promoCodeInterceptor.setOfferRedirect("market/products");
	promoCodeInterceptor.setErrorRedirect("invalidPromoCode");
	return promoCodeInterceptor;
	}
	/** 
	 * TO achive validatio with JSR-303 Implemented Hibernate validator
	 * @return
	 */
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
	LocalValidatorFactoryBean bean = new
	LocalValidatorFactoryBean();
	bean.setValidationMessageSource(messageSource());
	return bean;
	}
	/**
	 * Validator(Return type)  type must be 
	 * org.springframework.validation.Validator
	 */
	@Override
	public Validator getValidator(){
	return validator();
	}
	
	/**
	 * Combining Spring Validation and JSR-303 Validations.
	 * If we are not doing the following JSR303 validations are ignored by Spring MVC 
	 * when we are using Spring MVC validation to validate one property and JSR- 303 
	 * for one property unitstockvalidation is example for Spring MVC Validations
	 * @return
	 */
	
	@Bean
	public ProductValidator productValidator () {
	Set<Validator> springValidators = new HashSet<>();
	springValidators.add(new UnitsInStockValidator());
	ProductValidator productValidator = new
	ProductValidator();
	productValidator.setSpringValidators(springValidators);
	return productValidator;
	}
	
	
} 
