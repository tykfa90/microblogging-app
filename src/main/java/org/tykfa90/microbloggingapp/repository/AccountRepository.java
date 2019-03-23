package org.tykfa90.microbloggingapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tykfa90.microbloggingapp.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {}
