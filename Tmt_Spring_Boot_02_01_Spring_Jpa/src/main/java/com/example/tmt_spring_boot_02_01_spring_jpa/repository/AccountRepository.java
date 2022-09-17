package com.example.tmt_spring_boot_02_01_spring_jpa.repository;

import com.example.tmt_spring_boot_02_01_spring_jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String userName);
}
