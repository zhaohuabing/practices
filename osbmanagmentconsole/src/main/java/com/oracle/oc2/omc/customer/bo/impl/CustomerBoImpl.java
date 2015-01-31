package com.oracle.oc2.omc.customer.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.oracle.oc2.omc.customer.bo.CustomerBo;
import com.oracle.oc2.omc.customer.dao.CustomerDao;
import com.oracle.oc2.omc.customer.model.Customer;

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