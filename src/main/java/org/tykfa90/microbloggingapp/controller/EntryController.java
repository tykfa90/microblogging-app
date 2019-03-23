package org.tykfa90.microbloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.dto.EntryDTO;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.repository.AccountRepository;
import org.tykfa90.microbloggingapp.repository.EntryRepository;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/microblog/entries")
public class EntryController {

    Logger LOG = Logger.getLogger(EntryController.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> getAllEntries() {
        LOG.info("Displaying all entries");
        return accountRepository.findAll();
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntry(@RequestBody EntryDTO entryDto) {
        Entry entry = new Entry();
        entry.setAccountId(entryDto.getAccountId());
        entry.setEntryText(entryDto.getEntryText());
        entryRepository.save(entry);

        LOG.info("Added new entry");
    }
}
