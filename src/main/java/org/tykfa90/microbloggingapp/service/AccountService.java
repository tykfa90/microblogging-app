package org.tykfa90.microbloggingapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findByUsername(String username) {
        return accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user by the name: " + username + " found"));
    }
}
