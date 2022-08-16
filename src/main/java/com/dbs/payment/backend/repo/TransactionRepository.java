package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.Transaction;

import com.dbs.payment.backend.response.testResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {


    @Query(value="select * from transaction where customer_id =:customer_id ORDER BY transferdate DESC",nativeQuery = true)
    List<Transaction>  findByCustomerId(@PathVariable("customer_id")String customer_id);


    @Query(value = "select message_code,COUNT(transactionid) AS sum from transaction GROUP BY message_code;",nativeQuery = true)
    List<Object> findMessageCount();

    @Query(value = "select transfer_type_code,COUNT(transactionid) AS sum from transaction GROUP BY transfer_type_code",nativeQuery = true)
    List<Object> findTransferTypeCount();


    @Query(value = "select customer_id,SUM(inramount+transferfee) AS total from transaction GROUP BY customer_id ORDER BY total DESC LIMIT 5",nativeQuery = true)
    List<Map<String,Integer>> findTopByCutsomers();

    @Query(value = "select receiver_bic,SUM(inramount+transferfee) AS total from transaction GROUP BY receiver_bic ORDER BY total DESC LIMIT 5",nativeQuery = true)
    List<Map<String,Integer>> findTopByBanks();


}
