package com.mkyong.customer.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.mkyong.customer.bo.CustomerBo;
import com.mkyong.customer.dao.CustomerDao;
import com.mkyong.customer.model.Customer;

public class CustomerBoImpl implements CustomerBo {

	List<Customer> dao = new ArrayList<Customer>();

	public void setCustomerDao(CustomerDao customerDao) {

	}

	public void addCustomer(Customer customer) {

		dao.add(customer);

	}

	public List<Customer> findAllCustomer() {

		return dao;
	}
}