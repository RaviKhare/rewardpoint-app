/**
 * 
 */
package com.sun.rewardpoint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author rk250449
 *
 */

@Entity
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trxnId;
	
	@Column(name= "trxn_description")
	private String trxnDescription;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private  Customer customer;
	
	@Column(name="trxn_amount")
	private  Double trxnAmnout;

	@Column(name="trxn_date")
	private  Date trxnDate;
	
	
	
	/**
	 * @param trxnId
	 * @param trxnDate
	 * @param customer
	 * @param trxnAmnout
	 */
	public TransactionDetails( @JsonProperty("trxnDescription") String trxnDescription, @JsonUnwrapped Customer customer, @JsonProperty("trxnAmount") Double trxnAmnout,@JsonProperty("trxnDate") Date trxnDate) {
		super();
		this.trxnDescription = trxnDescription;
		this.customer = customer;
		this.trxnAmnout = trxnAmnout;
		this.trxnDate = trxnDate;
		
	}

	/**
	 * 
	 */
	public TransactionDetails() {
		super();
	}
	
	public Long getTrxnId() {
		return trxnId;
	}

	public void setTrxnId(Long trxnId) {
		this.trxnId = trxnId;
	}

	public Date getTrxnDate() {
		return  trxnDate;
	}

	public void setTrxnDate(Date trxnDate) {
		  this.trxnDate = trxnDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getTrxnAmnout() {
		return trxnAmnout;
	}

	public void setTrxnAmnout(Double trxnAmnout) {
		this.trxnAmnout = trxnAmnout;
	}

	public String getTrxnDescription() {
		return trxnDescription;
	}

	public void setTrxnDescription(String trxnDescription) {
		this.trxnDescription = trxnDescription;
	}

}
