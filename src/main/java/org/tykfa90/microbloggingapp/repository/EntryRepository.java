package org.tykfa90.microbloggingapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tykfa90.microbloggingapp.model.Entry;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {}
