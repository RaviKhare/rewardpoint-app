/**
 * 
 */
package com.sun.rewardpoint.model;

/**
 * @author rk250449
 *
 */
public final class RewardDetailKey {
	private  Long customerId;
	private  String monthlyName;
	
	
	
	/**
	 * @param customerId
	 * @param monthlyName
	 */
	public RewardDetailKey( Long customerId,  String monthlyName) {
		super();
		this.customerId = customerId;
		this.monthlyName = monthlyName;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId( Long customerId) {
		this.customerId = customerId;
	}
	public String getMonthlyName() {
		return monthlyName;
	}
	public void setMonthlyName(String monthlyName) {
		this.monthlyName = monthlyName;
	}

	@Override
	public String toString() {
		return "RewardDetailKey [customerId=" + customerId + ", monthlyName=" + monthlyName + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((monthlyName == null) ? 0 : monthlyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RewardDetailKey other = (RewardDetailKey) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (monthlyName == null) {
			if (other.monthlyName != null)
				return false;
		} else if (!monthlyName.equals(other.monthlyName))
			return false;
		return true;
	}
	

}
