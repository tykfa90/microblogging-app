package org.tykfa90.microbloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.repository.AccountRepository;

import java.util.Date;

@Controller
@RequestMapping(path = "/microblog")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewAccount(@RequestBody Account account) {
        Account accountToSave = new Account();
        accountToSave.setLogin(account.getLogin());
        accountToSave.setPassword(account.getPassword());
        accountToSave.setDisplayName(account.getDisplayName());
        accountToSave.setStatus(account.getStatus());
        accountToSave.setVisibility(account.getVisibility());
        Date date = new Date();
        accountToSave.setCreationDate(date);
        return "Added";
    }
}
