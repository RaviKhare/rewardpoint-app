/**
 * 
 */
package com.sun.rewardpoint.service;

import java.util.LinkedList;
import java.util.List;

import com.sun.rewardpoint.model.RewardDetails;
import com.sun.rewardpoint.model.TransactionDetails;

/**
 * @author rk
 *
 */

public interface TransactionService{
	
		List<TransactionDetails> getAllTransactionDetails() ;

		LinkedList<TransactionDetails> getLastThreeMonthAllTrxns() ;
		
		List<RewardDetails> getRewardPointDetails();
	    
	}
