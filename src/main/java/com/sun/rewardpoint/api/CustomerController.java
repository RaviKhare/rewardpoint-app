/**
 * 
 */
package com.sun.rewardpoint.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.rewardpoint.model.Customer;
import com.sun.rewardpoint.service.impl.CustomerServiceImpl;

/**
 * @author rk
 *
 */

@RequestMapping("/api/customer")
@RestController
public class CustomerController {
	
	@Autowired
	private final CustomerServiceImpl customerService;

	
	public CustomerController(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping(path="/create")
	public void addCustomer(@RequestBody Customer customer) {
	   customerService.addCustomer(customer);
	 }
	
	@GetMapping(path="getAllCustomer")
	public List<Customer> getAllCustomer() {
		 return customerService.getAllCustomer();
	 }
}
