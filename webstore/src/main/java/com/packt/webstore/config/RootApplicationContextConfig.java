package com.packt.webstore.config; 
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
@Configuration 
@ComponentScan("com.packt.webstore")
public class RootApplicationContextConfig {  

/**	
@Bean  
public DataSource dataSource() {   
	EmbeddedDatabaseBuilder builder = new     EmbeddedDatabaseBuilder(); 
	EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL) 
								.addScript("db/sql/create-table.sql")   
								.addScript("db/sql/insert-data.sql")  
								.build();      
	return db;    
	}     
	
	 
**/
	
	
	
	@Bean  
	public DataSource dataSource() {   
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        //MySQL database we are using
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/webstore");//change url
	        dataSource.setUsername("root");//change userid
	        dataSource.setPassword("root");//change pwd  
		return dataSource;    
		}     
		
	
	
	
	
	
@Bean    
public NamedParameterJdbcTemplate getJdbcTemplate() {    
	return new NamedParameterJdbcTemplate(dataSource()); 
	     }  
} 