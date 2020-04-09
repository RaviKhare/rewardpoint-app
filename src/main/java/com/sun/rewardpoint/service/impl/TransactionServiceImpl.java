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
 * @author rk
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private final TransactionRepository trxnRepository;
	
	private final static long MONTHS_MINUS =3 ;
	private static Long totalRewardPoints=0l ;
	private static Long customerIdValue=0l;
	private final static int TRXN_AMOUNT_HUNDRED = 100;
	private final static int TRXN_AMOUNT_FIFTY = 50;
	
	private final static int REWARD_POINT_VALUE_OVER_HUNDRED = 2;
	private final static int REWARD_POINT_VALUE_OVER_FIFTY = 1;
	
	
	 
	 /**
	 * @param transactionDAO
	 */
	public TransactionServiceImpl(TransactionRepository trxnRepository) {
		super();
		this.trxnRepository = trxnRepository;
	}
	
	@Override
	public List<TransactionDetails> getAllTransactionDetails() {
		return trxnRepository.findAll();
	}

	@Override
	public LinkedList<TransactionDetails> getLastThreeMonthAllTrxns() {
		return trxnRepository.findAllWithTrxnDateAfter(getThreeMonthEarlierDate());
	}
	
	@Override 
	public List<RewardDetails> getRewardPointDetails(){
	    	Map<RewardDetailKey, RewardDetails> monthlyRewardMap =  new LinkedHashMap<>();
			
    		List<TransactionDetails> allTrxn = getLastThreeMonthAllTrxns();
    		allTrxn.stream().forEachOrdered(trxn -> 
    		{
    				RewardDetailKey rewardDetailKey = new RewardDetailKey(trxn.getCustomer().getCustomerId(), getMonthName(trxn.getTrxnDate()));
    				
    				if(	monthlyRewardMap.containsKey(rewardDetailKey)) {
    						RewardDetails rewardDetails= monthlyRewardMap.get(rewardDetailKey);
    						rewardDetails.setMonthlyTrxnAmount(getMonthWiseTrxnAmount(monthlyRewardMap, trxn, rewardDetailKey));
    						processTrxnsForRewardPoints(rewardDetails, trxn);
    						monthlyRewardMap.put(rewardDetailKey, rewardDetails);
    				}else {
    						RewardDetails rewardDetails = getRewardDetails(trxn);
    						processTrxnsForRewardPoints(rewardDetails, trxn);
    						monthlyRewardMap.put(rewardDetailKey, rewardDetails);
    						customerIdValue = rewardDetailKey.getCustomerId();
    				}
    			});
    		
    			return monthlyRewardMap.values().stream().collect(Collectors.toList());
    		
		}

		
 
		private void processTrxnsForRewardPoints(RewardDetails rewardDetails, TransactionDetails trxn) {
				
		    long trxnRewardPoints = calculateTrxnWiseRewardPoints(trxn);
		    
		    rewardDetails.setMonthlyRewardPoints(calculateMonthWiseRewardPoints(rewardDetails, trxnRewardPoints));
		    
		    calculatedTotalRewardPoints(rewardDetails, trxn, trxnRewardPoints);
	    }

		
		
		private void calculatedTotalRewardPoints(RewardDetails rewardDetails, TransactionDetails trxn,	long trxnRewardPoints) {
			if (trxnRewardPoints > 0l ) {
				    reinitializeTotalRewarPointsValueBasedOnUser(rewardDetails, trxn);
					rewardDetails.setTotalRewardPoints(totalRewardPoints +  trxnRewardPoints);
					totalRewardPoints = rewardDetails.getTotalRewardPoints();
			}else {
				 reinitializeTotalRewarPointsValueBasedOnUser(rewardDetails, trxn);
				 rewardDetails.setTotalRewardPoints(totalRewardPoints);
			}
		}
		
		private long calculateMonthWiseRewardPoints(RewardDetails rewardDetails, long trxnRewardPoints) {
			return rewardDetails.getMonthlyRewardPoints() == null ? trxnRewardPoints : (rewardDetails.getMonthlyRewardPoints() + trxnRewardPoints);
		}
		
		private long calculateTrxnWiseRewardPoints(TransactionDetails trxn) {
			long trxnRewardPoints = 0l;
		    long trxnAmount = Math.round(trxn.getTrxnAmnout());
		    
		    if (trxnAmount > TRXN_AMOUNT_FIFTY && trxnAmount <= TRXN_AMOUNT_HUNDRED)
		    	 trxnRewardPoints =  (trxnAmount -TRXN_AMOUNT_FIFTY )  ;
		    if (trxnAmount > TRXN_AMOUNT_HUNDRED) {	
		    		 trxnRewardPoints = (TRXN_AMOUNT_FIFTY * REWARD_POINT_VALUE_OVER_FIFTY)  + (REWARD_POINT_VALUE_OVER_HUNDRED * (trxnAmount - TRXN_AMOUNT_HUNDRED ));
		    	}
			return trxnRewardPoints;
		}
		
		private double getMonthWiseTrxnAmount(Map<RewardDetailKey, RewardDetails> monthlyRewardMap, TransactionDetails trxn, RewardDetailKey rewardDetailKey) {
			return monthlyRewardMap.get(rewardDetailKey).getMonthlyTrxnAmount() + trxn.getTrxnAmnout();
		}
		
		 private void reinitializeTotalRewarPointsValueBasedOnUser(RewardDetails rewardDetails, TransactionDetails trxn) {
			if(totalRewardPoints > 0 && rewardDetails.getTotalRewardPoints().equals(0l) && !customerIdValue.equals(trxn.getCustomer().getCustomerId())) {
						totalRewardPoints = 0l;
			 }
		 }
		 
		 private RewardDetails getRewardDetails(TransactionDetails trxn) {
			 RewardDetails rewardDetails = new RewardDetails(trxn.getCustomer(), getMonthName(trxn.getTrxnDate()), trxn.getTrxnAmnout(), 0l, 0l);
			 return rewardDetails;
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

		
	  
	    
}
