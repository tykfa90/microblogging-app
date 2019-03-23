package org.tykfa90.microbloggingapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.repository.AccountRepository;
import org.tykfa90.microbloggingapp.repository.EntryRepository;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/microblog/accounts")
public class AccountController {

    Logger LOG = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> getAllAccounts() {
        LOG.info("Displaying all accounts");
        return accountRepository.findAll();
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAccount(@RequestBody Account account) {

        accountRepository.save(account);

        LOG.info("Added new account");
    }
}
