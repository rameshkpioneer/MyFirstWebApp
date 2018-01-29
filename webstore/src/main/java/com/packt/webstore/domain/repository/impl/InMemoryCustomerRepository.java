package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Override
	public List<Customer> getAllCutomers() {
		// TODO Auto-generated method stub
		 Map<String, Object> params = new HashMap<String, Object>();  
		 List<Customer> result = jdbcTemplate.query("SELECT *   FROM customer", params, new CustomerMapper()); 
		 return result; 
	}
	 private static final class CustomerMapper implements   RowMapper<Customer> {  
		
		 public Customer mapRow(ResultSet rs, int rowNum)throws SQLException {  
			 Customer customer = new Customer(); 
			 customer.setcusstomerId(rs.getInt("ID"));
			 customer.setCustomer_name(rs.getString("NAME"));
			 customer.setAddress(rs.getString("ADRESS"));
			 customer.setNo_of_orders_made(rs.getInt("NOOFORDERS"));
            return customer;  
            } 
		 
}
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		String SQL = "INSERT INTO customer (ID, "+ "NAME,"
				+ "ADRESS,"
				+ "NOOFORDERS) "
				+ "VALUES (:id, :name, :adress, :nooforders)";
				Map<String, Object> params = new HashMap<>();
				params.put("id",customer.getcusstomerId());
				params.put("name", customer.getCustomer_name());
				params.put("adress", customer.getAddress());
				params.put("nooforders", customer.getNo_of_orders_made());
				jdbcTemplate.update(SQL, params);
		
		
	}
}