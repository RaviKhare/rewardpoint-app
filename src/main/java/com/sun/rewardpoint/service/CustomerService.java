/**
 * 
 */
package com.sun.rewardpoint.service;

import java.util.List;

import com.sun.rewardpoint.model.Customer;

/**
 * @author rk250449
 *
 */


public interface CustomerService {

		public Customer addCustomer(Customer customer) ;
	
		public List<Customer> getAllCustomer();
	
}
