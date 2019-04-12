package org.tykfa90.microbloggingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.dto.EntryDTO;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.service.AccountService;
import org.tykfa90.microbloggingapp.service.EntryService;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/entries")
public class EntryController {

    private Logger LOG = Logger.getLogger(EntryController.class.getName());

    private EntryService entryService;
    private AccountService accountService;

    public EntryController(EntryService entryService, AccountService accountService) {
        this.entryService = entryService;
        this.accountService = accountService;
    }

    //All entries
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Entry> getAllEntries() {
        LOG.info("Displaying all entries");
        return entryService.findAllEntries();
    }

    //Entries by account id
    @GetMapping(path = "/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Entry> getAllEntriesByAccountId(@PathVariable Long accountId) {
        LOG.info("Displaying all entries created by user with id: " + accountId);
        return entryService.findAllEntriesByAccountId(accountId);
    }

    //Add entry
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntry(@RequestBody EntryDTO entryDto, Principal principal) {
        Entry entry = new Entry();
        Long requestSenderAccountId = accountService.findByUsername(principal.getName()).getId();
        entry.setAccountId(requestSenderAccountId);
        entry.setEntryText(entryDto.getEntryText());
        entryService.saveEntry(entry);
        LOG.info("Added new entry");
    }

    //Delete entry
    @DeleteMapping(path = "/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeEntry(@PathVariable Long entryId, Principal principal) {
        Long requestSenderAccountId = entryService.findEntryById(entryId).getAuthorId();
        Long entryToDeleteAuthorId = accountService.findByUsername(principal.getName()).getId();
        if (requestSenderAccountId.equals(entryToDeleteAuthorId)) {
            entryService.deleteEntryById(entryId);
            LOG.info("Removing entry with provided ID " + entryId);
        } //TODO error handlng and delegation
    }

    //Update entry
    @PatchMapping(path = "/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEntry(@PathVariable Long entryId, @RequestBody EntryDTO entryDTO, Principal principal) {
        Long requestSenderAccountId = accountService.findByUsername(principal.getName()).getId();
        Entry entryToUpdate = entryService.findEntryById(entryId);
        Long authorId = entryToUpdate.getAuthorId();
        if (requestSenderAccountId.equals(authorId)) {
            entryToUpdate.setEntryText(entryDTO.getEntryText());
            entryService.saveEntry(entryToUpdate);
            LOG.info("Updating entry with id: " + entryId);
        } //TODO error handling and delegation
    }
}
