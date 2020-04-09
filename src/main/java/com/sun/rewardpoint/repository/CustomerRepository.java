/**
 * 
 */
package com.sun.rewardpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.rewardpoint.model.Customer;

/**
 * @author rk
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	
}
