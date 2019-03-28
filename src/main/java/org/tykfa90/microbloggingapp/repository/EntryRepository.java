package org.tykfa90.microbloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tykfa90.microbloggingapp.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {}
