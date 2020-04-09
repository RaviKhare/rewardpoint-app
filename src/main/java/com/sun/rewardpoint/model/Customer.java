/**
 * 
 */
package com.sun.rewardpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rk
 *
 */

@Entity(name="customer")
public class Customer {
	
	@Id
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	 	
	
	
	/**
	 * 
	 */
	public Customer() {
		super();
	}


	/**
	 * @param customerId
	 * @param customerName
	 */
	public Customer(@JsonProperty("id")Long customerId, @JsonProperty("name") String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}


	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + "]";
	}
	
	
	
	

}
