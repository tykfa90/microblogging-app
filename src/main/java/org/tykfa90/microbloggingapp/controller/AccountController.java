package org.tykfa90.microbloggingapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.model.AccountStatus;
import org.tykfa90.microbloggingapp.service.AccountService;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    private PasswordEncoder passwordEncoder;
    private AccountService accountService;

    private Logger LOG = Logger.getLogger(AccountController.class.getName());

    public AccountController(PasswordEncoder passwordEncoder, AccountService accountService) {
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    //Displays all accounts
    @GetMapping("/all")
    public List<Account> showAccounts() {
        LOG.info("Displaying all accounts");
        return accountService.findAllAccounts();
    }

    //Displays single account by it's username
    @GetMapping
    public Account showSpecificAccount(@RequestBody String username) {
        LOG.info("Displaying specified account with username: " + username);
        return accountService.findByUsername(username);
    }

    //Account creation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAccount(@RequestBody Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.saveAccount(account);
        LOG.info("Added new account");
    }

    //Account deactivation by user action
    @DeleteMapping
    public void deleteAccount(Principal principal) {
        String name = principal.getName();
        Account accountToDeactivate = accountService.findByUsername(name);
        accountToDeactivate.setStatus(AccountStatus.BLOCKED);
        accountService.saveAccount(accountToDeactivate);
        SecurityContextHolder.clearContext();
        LOG.info("Deactivating account");
    }

    //Account update
    @PutMapping
    public void editAccount(@RequestBody Account account, Principal principal) {
        Account accountToUpdate = accountService.findByUsername(principal.getName());
        accountToUpdate.setStatus(account.getStatus());
        accountToUpdate.setDisplayName(account.getDisplayName());
        accountToUpdate.setPassword(account.getPassword());
        accountService.saveAccount(accountToUpdate);
        LOG.info("Account " + principal.getName() + " has been edited");
    }
}
