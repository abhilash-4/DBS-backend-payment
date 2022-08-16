package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanksRepository extends JpaRepository<Banks,String> {
}
