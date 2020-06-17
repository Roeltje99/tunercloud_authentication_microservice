package com.tunercloud.authentication_microservice.models;

import com.tunercloud.authentication_microservice.data.IAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountDetailsAuthentication implements UserDetailsService {
    private IAccountRepository accountRepository;

    public AccountDetailsAuthentication(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }
        return new JwtAccount(account.getId(), account.getEmail(), account.getPassword(), Collections.emptyList());
    }
}
