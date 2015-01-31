package com.oracle.oc2.omc.customer.bo;
 
import java.util.List;
 
import com.oracle.oc2.omc.customer.model.Customer;
 
public interface CustomerBo{
 
	void addCustomer(Customer customer);
 
	List<Customer> findAllCustomer();
 
}