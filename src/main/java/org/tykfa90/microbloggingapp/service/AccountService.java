package org.tykfa90.microbloggingapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.repository.AccountRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user by the name: " + username + " found"));
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void removeAccount(Long id) {
        String errorMsg = "Account not found";
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(errorMsg);
        }
    }
}
