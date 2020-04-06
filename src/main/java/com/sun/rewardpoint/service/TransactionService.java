/**
 * 
 */
package com.sun.rewardpoint.service;

import java.util.LinkedList;
import java.util.List;

import com.sun.rewardpoint.model.TransactionDetails;

/**
 * @author rk250449
 *
 */

public interface TransactionService{
	
		public List<TransactionDetails> getAllTransactionDetails() ;

		public LinkedList<TransactionDetails> getLastThreeMonthAllTrxns() ;
	    
	}
