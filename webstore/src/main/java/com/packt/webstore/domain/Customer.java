
package com.packt.webstore.domain;

public class Customer {

	private int cusstomerId;
	
	private String customer_name;
	
	private String address;
	
	private  int no_of_orders_made;

	
	public int getcusstomerId() {
		return cusstomerId;
	}

	public void setcusstomerId(int id) {
		this.cusstomerId = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNo_of_orders_made() {
		return no_of_orders_made;
	}

	public void setNo_of_orders_made(int no_of_orders_made) {
		this.no_of_orders_made = no_of_orders_made;
	}

	
}
