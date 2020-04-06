/**
 * 
 */
package com.sun.rewardpoint.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.rewardpoint.model.RewardDetailKey;
import com.sun.rewardpoint.model.RewardDetails;
import com.sun.rewardpoint.model.TransactionDetails;
import com.sun.rewardpoint.repository.TransactionRepository;
import com.sun.rewardpoint.service.TransactionService;

/**
 * @author rk250449
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private final TransactionRepository trxnRepository;
	
	private final static long MONTHS_MINUS =3 ;
	private static Integer totalRewardPoints=0 ;
	private static Long customerIdValue=0l;
	private final static int TRXN_AMOUNT_HUNDRED = 100;
	private final static int TRXN_AMOUNT_FIFTY = 50;
	
	private final static int REWARD_POINT_SPENT_OVER_HUNDRED = 2;
	private final static int REWARD_POINT_SPENT_OVER_FIFTY = 1;
	
	
	 
	 /**
	 * @param transactionDAO
	 */
	public TransactionServiceImpl(TransactionRepository trxnRepository) {
		super();
		this.trxnRepository = trxnRepository;
	}

	public List<TransactionDetails> getAllTransactionDetails() {
		return trxnRepository.findAll();
	}

	public LinkedList<TransactionDetails> getLastThreeMonthAllTrxns() {
		return trxnRepository.findAllWithTrxnDateAfter(getThreeMonthEarlierDate());
	}
	
	  
	public List<RewardDetails> calculateUserWiseRewardPoints(){
	    	Map<RewardDetailKey, RewardDetails> monthlyRewardMap =  new LinkedHashMap<>();
			
    		List<TransactionDetails> allTrxn = getLastThreeMonthAllTrxns();
    		allTrxn.stream().forEachOrdered(trxn -> 
    		{
    				RewardDetailKey rewardDetailKey = new RewardDetailKey(trxn.getCustomer().getCustomerId(), getMonthName(trxn.getTrxnDate()));
    				
    				if(	monthlyRewardMap.containsKey(rewardDetailKey)) {
    					Double monthlyTrxnAmount = monthlyRewardMap.get(rewardDetailKey).getMonthlyTrxnAmount() + trxn.getTrxnAmnout() ; ;
        				RewardDetails rewardDetails= monthlyRewardMap.get(rewardDetailKey);
        				rewardDetails.setMonthlyTrxnAmount(monthlyTrxnAmount);
        				calulatedTrxnRewardPoints(rewardDetails, trxn);
    					monthlyRewardMap.put(rewardDetailKey, rewardDetails);
    				}else {
    					
    					RewardDetails rewardDetails = new RewardDetails(trxn.getCustomer(), getMonthName(trxn.getTrxnDate()), trxn.getTrxnAmnout(), 0, 0);
    					calulatedTrxnRewardPoints(rewardDetails, trxn);
    					monthlyRewardMap.put(rewardDetailKey, rewardDetails);
    					customerIdValue = rewardDetailKey.getCustomerId();
    				}
    		});
    		
    		return monthlyRewardMap.values().stream().collect(Collectors.toList());
    		
	}
	    
	private void calulatedTrxnRewardPoints(RewardDetails rewardDetails, TransactionDetails trxn) {
	    Integer trxnRewardPoints = 0;
	    Double trxnAmnout = trxn.getTrxnAmnout();
	    if (trxnAmnout > TRXN_AMOUNT_HUNDRED) {
	    		int reminderValue =  (int) (trxnAmnout % 100);
	    		trxnRewardPoints = reminderValue * REWARD_POINT_SPENT_OVER_HUNDRED ;
	    		trxnRewardPoints += TRXN_AMOUNT_FIFTY * REWARD_POINT_SPENT_OVER_FIFTY;
	    	}
	    rewardDetails.setMonthlyRewardPoints(rewardDetails.getMonthlyRewardPoints() == null ? trxnRewardPoints : (rewardDetails.getMonthlyRewardPoints()+trxnRewardPoints));
	    if (trxnRewardPoints > 0 ) {
			    reinitializeTotalRewarPointsValueBasedOnUser(rewardDetails, trxn);
				rewardDetails.setTotalRewardPoints(totalRewardPoints +  trxnRewardPoints);
				totalRewardPoints = rewardDetails.getTotalRewardPoints();
		}else {
			 reinitializeTotalRewarPointsValueBasedOnUser(rewardDetails, trxn);
			 rewardDetails.setTotalRewardPoints(totalRewardPoints);
		}
    }

	 
	    public Date getThreeMonthEarlierDate() {
	    	LocalDate currentDate = LocalDate.now();
	    	LocalDate threeMonthEarlierDate  = currentDate.minusMonths(MONTHS_MINUS);
	    	return java.sql.Date.valueOf(threeMonthEarlierDate);
	    }
	
		    
	    public String getMonthName(Date dateToConvert) {
	    	return	convertDateToLocalDate(dateToConvert).getMonth().toString();
	    }
	    
	    public LocalDate convertDateToLocalDate(Date dateToConvert) {
	        return dateToConvert.toInstant()
	          .atZone(ZoneId.systemDefault())
	          .toLocalDate();
	    }
	   
	    private void reinitializeTotalRewarPointsValueBasedOnUser(RewardDetails rewardDetails, TransactionDetails trxn) {
			if(totalRewardPoints > 0 && rewardDetails.getTotalRewardPoints().equals(0) && !customerIdValue.equals(trxn.getCustomer().getCustomerId())) {
					totalRewardPoints = 0;
			 }
		}
	    
}
