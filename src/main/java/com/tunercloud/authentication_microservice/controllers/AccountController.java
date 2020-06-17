package com.tunercloud.authentication_microservice.controllers;

import com.tunercloud.authentication_microservice.logic.AccountLogic;
import com.tunercloud.authentication_microservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController
{
    private AccountLogic accountLogic;

    @Autowired
    public AccountController(AccountLogic accountLogic) { this.accountLogic = accountLogic; }


    @PostMapping(path = "/register")
    public Account addAccount(@RequestBody Account account) {
        return accountLogic.addAccount(account);
    }

    @GetMapping(path="")
    public Iterable<Account> getAllAccounts() {
        return accountLogic.getAllAccounts();
    }

    @GetMapping("/{accountId:[0-9]+}")
    public Account getAccount(@PathVariable int accountId) {
        return accountLogic.getAccount(accountId);
    }

    @PutMapping(path="")
    public Account updateAccount(@RequestBody Account account) {
        return accountLogic.updateAccount(account);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path="")
    public void deleteAccount(@RequestBody Account account){
        accountLogic.deleteAccount(account);
    }
}
