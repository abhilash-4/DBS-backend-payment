package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,String> {

    Optional<Customers> findById(String id);

}
