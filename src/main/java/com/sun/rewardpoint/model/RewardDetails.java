/**
 * 
 */
package com.sun.rewardpoint.model;

/**
 * @author rk250449
 *
 */
public class RewardDetails {
	private Customer customer;
	private String monthName;
	private Double monthlyTrxnAmount;
	private Integer monthlyRewardPoints;
	private Integer totalRewardPoints;
	
	
	
	/**
	 * @param customer
	 * @param monthlyTrxnAmount
	 * @param monthlRewardPoints
	 * @param totalRewardPoints
	 */
	public RewardDetails(Customer customer, String monthName, Double monthlyTrxnAmount, Integer monthlRewardPoints,
			Integer totalRewardPoints) {
		super();
		this.customer = customer;
		this.monthName = monthName;
		this.monthlyTrxnAmount = monthlyTrxnAmount;
		this.monthlyRewardPoints = monthlRewardPoints;
		this.totalRewardPoints = totalRewardPoints;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Double getMonthlyTrxnAmount() {
		return monthlyTrxnAmount;
	}
	public void setMonthlyTrxnAmount(Double monthlyTrxnAmount) {
		this.monthlyTrxnAmount = monthlyTrxnAmount;
	}
	public Integer getMonthlyRewardPoints() {
		return monthlyRewardPoints;
	}
	public void setMonthlyRewardPoints(Integer monthlyRewardPoints) {
		this.monthlyRewardPoints = monthlyRewardPoints;
	}
	public Integer getTotalRewardPoints() {
		return totalRewardPoints;
	}
	public void setTotalRewardPoints(Integer totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

	@Override
	public String toString() {
		return "RewardDetails [customer=" + customer + ", monthName=" + monthName +", monthlyTrxnAmount=" + monthlyTrxnAmount
				+ ", monthlyRewardPoints=" + monthlyRewardPoints + ", totalRewardPoints=" + totalRewardPoints + "]";
	}
		
	
	

}
