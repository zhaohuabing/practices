package com.oracle.oc2.omc.customer.dao.impl;
 
import java.util.Date;
import java.util.List;
 
import com.oracle.oc2.omc.customer.dao.CustomerDao;
import com.oracle.oc2.omc.customer.model.Customer;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
 
public class CustomerDaoImpl extends 
       HibernateDaoSupport implements CustomerDao{
 
	public void addCustomer(Customer customer){
 
		customer.setCreatedDate(new Date());
		getHibernateTemplate().save(customer);
 
	}
 
	public List<Customer> findAllCustomer(){
 
		return getHibernateTemplate().find("from Customer");
 
	}
}