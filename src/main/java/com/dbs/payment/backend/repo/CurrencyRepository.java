package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,String> {

}
