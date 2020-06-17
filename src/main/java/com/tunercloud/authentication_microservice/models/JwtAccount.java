package com.tunercloud.authentication_microservice.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAccount extends org.springframework.security.core.userdetails.User
{
    private int accountId;

    public JwtAccount(int accountId, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }
}

