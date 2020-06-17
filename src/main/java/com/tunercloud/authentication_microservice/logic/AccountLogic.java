package com.tunercloud.authentication_microservice.logic;

import com.tunercloud.authentication_microservice.data.IAccountRepository;
import com.tunercloud.authentication_microservice.models.Account;
import com.tunercloud.authentication_microservice.rabbitmq.ArtistWrapper;
import com.tunercloud.authentication_microservice.rabbitmq.RabbitSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountLogic {

    private IAccountRepository accountRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RabbitSender rabbitSender;

    @Autowired
    public AccountLogic(IAccountRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RabbitSender rabbitSender) {
        this.accountRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rabbitSender = rabbitSender;
    }

    public Account addAccount(Account account) {
        Account testAccount = accountRepository.findAccountByEmail(account.getEmail());
        if (testAccount != null) {
            throw new IllegalArgumentException("Account already exists with the email: " + account.getEmail());
        }

        String password = account.getPassword();
        account.setPassword(bCryptPasswordEncoder.encode(password));

        Account addedAccount = accountRepository.save(account);

        if (addedAccount.getArtist()) {
            rabbitSender.sendAdd(new ArtistWrapper(addedAccount));
        }
        return addedAccount;
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(int accountId) {
        return accountRepository.findAccountById(accountId);
    }

    public Account updateAccount(Account account) {
        Account gottenAccount = accountRepository.findAccountById(account.getId());

        if (gottenAccount != null) {
            account.setId(gottenAccount.getId());

            String password = account.getPassword();
            account.setPassword(bCryptPasswordEncoder.encode(password));

            Account updatedAccount = accountRepository.save(account);
            if (updatedAccount.getArtist()) {
                rabbitSender.sendUpdate(new ArtistWrapper(updatedAccount));
            }
            return updatedAccount;
        }
        throw new IllegalArgumentException("Account not found");
    }

    public void deleteAccount(Account account) {
        Account gottenAccount = getAccount(account.getId());

        if (gottenAccount.getArtist()) {
            rabbitSender.sendDelete(new ArtistWrapper(gottenAccount));
        }

        accountRepository.delete(gottenAccount);
    }
}

