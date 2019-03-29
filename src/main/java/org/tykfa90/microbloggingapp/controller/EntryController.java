package org.tykfa90.microbloggingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.tykfa90.microbloggingapp.dto.EntryDTO;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.repository.AccountRepository;
import org.tykfa90.microbloggingapp.repository.EntryRepository;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/entries")
public class EntryController {

    private Logger LOG = Logger.getLogger(EntryController.class.getName());

    private AccountRepository accountRepository;

    private EntryRepository entryRepository;

    public EntryController(AccountRepository accountRepository, EntryRepository entryRepository) {
        this.accountRepository = accountRepository;
        this.entryRepository = entryRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Entry> getAllEntries() {
        LOG.info("Displaying all entries");
        return entryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntry(@RequestBody EntryDTO entryDto) {
        Entry entry = new Entry();
        entry.setAccountId(entryDto.getAccountId());
        entry.setEntryText(entryDto.getEntryText());
        entryRepository.save(entry);

        LOG.info("Added new entry");
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeEntry(@RequestBody Long entryId) {
        if (entryRepository.findById(entryId).isPresent()) {
            entryRepository.deleteById(entryId);
            LOG.info("Removing entry with provided ID " + entryId);
        } else {
            LOG.info("Entry with ID: " + entryId + " not found!");
        }
    }
}
