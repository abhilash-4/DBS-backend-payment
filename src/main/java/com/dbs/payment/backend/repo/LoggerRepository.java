package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger,Long> {

}
