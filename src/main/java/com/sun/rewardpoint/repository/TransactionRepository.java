/**
 * 
 */
package com.sun.rewardpoint.repository;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sun.rewardpoint.model.TransactionDetails;

/**
 * @author rk
 *
 */

public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {
	
	@Query("select trxn from TransactionDetails trxn where trxn.trxnDate >= :threeMonthEarlierDate order by trxn.customer.customerId, trxn.trxnDate ")
	LinkedList<TransactionDetails> findAllWithTrxnDateAfter(@Param("threeMonthEarlierDate") Date date);
}
