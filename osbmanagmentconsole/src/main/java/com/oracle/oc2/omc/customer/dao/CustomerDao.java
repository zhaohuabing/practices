package com.oracle.oc2.omc.customer.dao;
 
import java.util.List;
 
import com.oracle.oc2.omc.customer.model.Customer;
 
public interface CustomerDao{
 
	void addCustomer(Customer customer);
 
	List<Customer> findAllCustomer();
 
}