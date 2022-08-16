package com.dbs.payment.backend.repo;

import com.dbs.payment.backend.models.ERole;
import com.dbs.payment.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRolename(ERole rolename);

}
