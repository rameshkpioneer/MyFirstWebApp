  package com.packt.webstore.controller; 
  import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator; 
 
@Controller  
@RequestMapping("/market")
public class ProductController {  
	 
	  @Autowired
	  private ProductService productService;
	  
	  @Autowired
	  private UnitsInStockValidator unitsInStockValidator;
	  
	  @Autowired
	  private ProductValidator productValidator;
	  
	  @RequestMapping("/products")  
	  public String list(Model model) {  
		  model.addAttribute("products", productService.getAllProducts());
		System.out.println(" product controller");
		  return "products";  
      } 
	  
	  @RequestMapping("/update/stock")
	  public String updateStock(Model model) {
	  productService.updateAllStock();
	  return "redirect:/products";
	  }
	  
	  @RequestMapping("/products/{category}")
	  public String getProductsByCategory(Model model ,@PathVariable("category") String productCategory) {
		//System.out.println("Categaory " + productCategory);
		  
		  List<Product> products =
				  productService.getProductsByCategory(productCategory);
				  if (products == null || products.isEmpty()) {
				  throw new NoProductsFoundUnderCategoryException();
				  }
		  model.addAttribute("products",products);
	     return "products";
	  }
	  
		@RequestMapping("/products/filter/{params}")
		public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String,List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
		}
	  
	  
		@RequestMapping("/product")
		public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product",
		productService.getProductById(productId));
		return "product";
		}
		
	/**	
	 *  Need to Practice for this one  when  i found free time for this 
	 * @RequestMapping("/product/{category}/{price}")
		public String getProductByMC(@PathVariable("category") String productCategory,@MatrixVariable(pathVar="price") Map<String,List<String>> filterParams,Model model) {
			System.out.println(" Product category " +productCategory );
			System.out.println("Multiple Categaoriies");
			model.addAttribute("products", productService.getProductByMultipleVariable(productCategory, filterParams, "Google"));
			System.out.println("After getiing Product details");
		return "products";
		}  
		**/
		
		
		
		
		@RequestMapping(value = "/products/add", method =RequestMethod.GET)
				public String getAddNewProductForm(Model model) {
				Product newProduct = new Product();
				model.addAttribute("newProduct", newProduct);
				return "addProduct";
				}
			
		 
		
		
		//Commented code is for  to choose which field should allowed to store in DB
		/*System.out.println("Suppressed filds "+result.getSuppressedFields().length);	
		System.out.println("Units in Order ...  "+ newProduct.getUnitsInOrder());
		System.out.println("Units in Order ...  "+ newProduct.isDiscontinued());
		String[] sFeilds = result.getSuppressedFields();
		if(sFeilds.length > 0){
			throw new RuntimeException("Attempted to bind disallowed fields: "+StringUtils.arrayToCommaDelimitedString(sFeilds));
		}
		*/
		@RequestMapping(value = "/products/add", method =RequestMethod.POST)
		public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result,HttpServletRequest request) {
		
			if(result.hasErrors()) {
				return "addProduct";
				}
			
			MultipartFile productImage = newProduct.getProductImage();
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");
			if (productImage!=null && !productImage.isEmpty()) {
			try {
			productImage.transferTo(new File(rootDirectory+"resources/images/"+newProduct.getProductId()+".png"));
			} catch (Exception e) {
			throw new RuntimeException("Product Image saving failed", e);
			}
			}
			
	//	productService.addProduct(newProduct);
		return "redirect:/market/products";
		}
		/**
		 * 
		 */
		@InitBinder
		 public void initialiseBinder(WebDataBinder binder) {
			 binder.setAllowedFields("productId","name", "unitPrice", "description","productPdf",
                             "language","manufacturer", "category","unitsInStock*","condition","productImage");

	     /*  binder.setDisallowedFields("discontinued","unitsInStock");	
	 		 Spring Validator.
			 binder.setValidator(unitsInStockValidator); */ 
			 binder.setValidator(productValidator);
			 
		
		
		}
	
		@ExceptionHandler(ProductNotFoundException.class)
		public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
		}
		
		
		@RequestMapping("/products/invalidPromoCode")
		public String invalidPromoCode() {
		return "invalidPromoCode";
		}
		
		
}