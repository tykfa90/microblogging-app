package org.tykfa90.microbloggingapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.repository.AccountRepository;
import org.tykfa90.microbloggingapp.repository.EntryRepository;
import org.tykfa90.microbloggingapp.service.AccountService;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    private PasswordEncoder passwordEncoder;
    private AccountService accountService;

    private Logger LOG = Logger.getLogger(AccountController.class.getName());

    public AccountController(PasswordEncoder passwordEncoder, AccountRepository accountRepository, EntryRepository entryRepository, AccountService accountService) {
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @GetMapping
    public String showAccounts() {
        return "łolaboga";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAccount(@RequestBody Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.saveAccount(account);
        LOG.info("Added new account");
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeAccount(@PathVariable Long id) {
//        if (principa.id == id) {
////            accountService.removeAccount(id);
////        } else {
////            t
////        }TODO
    }
}
