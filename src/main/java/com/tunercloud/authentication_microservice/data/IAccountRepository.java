package com.tunercloud.authentication_microservice.data;

import com.tunercloud.authentication_microservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByEmail (String email);
    Account findAccountById (int id);
}

