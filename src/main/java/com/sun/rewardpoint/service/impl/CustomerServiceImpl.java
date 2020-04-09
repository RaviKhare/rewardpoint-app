/**
 * 
 */
package com.sun.rewardpoint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.rewardpoint.model.Customer;
import com.sun.rewardpoint.repository.CustomerRepository;
import com.sun.rewardpoint.service.CustomerService;

/**
 * @author rk
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private final CustomerRepository customerRepository;
	
	 
	 public CustomerServiceImpl(CustomerRepository customerRepository) {
		 super();
		 this.customerRepository = customerRepository;
		 }
	
	 public Customer addCustomer(Customer customer) {
		 return customerRepository.save(customer);
	 }
	
	  public List<Customer> getAllCustomer(){
		  return customerRepository.findAll();
	  }

}
