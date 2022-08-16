package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.MessageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageCodeRepository extends JpaRepository<MessageCode,String> {
}
