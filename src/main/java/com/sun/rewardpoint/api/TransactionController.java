/**
 * 
 */
package com.sun.rewardpoint.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.rewardpoint.model.RewardDetails;
import com.sun.rewardpoint.model.TransactionDetails;
import com.sun.rewardpoint.service.impl.TransactionServiceImpl;


/**
 * @author rk
 *
 */

@RequestMapping("/api/trxn")
@RestController
public class TransactionController {
	
	@Autowired
	private final TransactionServiceImpl transactionService;

	/**
	 * @param transactionService
	 */
	public TransactionController(TransactionServiceImpl transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@GetMapping(path="/getAllTrxn")
	public List<TransactionDetails> getLastThreeMonthAllTrxns() {
		return transactionService.getLastThreeMonthAllTrxns();
	}
	
	@GetMapping(path="/getLastThreeMonthAlllTrxn")
	public List<TransactionDetails> getAllTransactionDetails() {
		return transactionService.getAllTransactionDetails();
	}
	
	@GetMapping(path="/getRewardPointDetails")
	public List<RewardDetails> getRewardPointDetails() {
		return	transactionService.getRewardPointDetails();
	}	
}
