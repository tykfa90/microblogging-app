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

    //Entries by entryAuthor
    @GetMapping(path = "/{entryAuthor}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Entry> getAllEntriesByEntryAuthor(@PathVariable String entryAuthor) {
        LOG.info("Displaying all entries created by user with id: " + entryAuthor);
        return entryService.findAllEntriesByEntryAuthor(entryAuthor);
    }

    //Add entry
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntry(@RequestBody EntryDTO entryDto, Principal principal) {
        Entry entry = new Entry();
        String requestSenderAccountName = principal.getName();
        entry.setEntryAuthor(requestSenderAccountName);
        entry.setEntryText(entryDto.getEntryText());
        entryService.saveEntry(entry);
        LOG.info("Added new entry");
    }

    //Delete entry
    @DeleteMapping(path = "/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeEntry(@PathVariable Long entryId, Principal principal) {
        String requestSenderAccountName = entryService.findEntryById(entryId).getEntryAuthor();
        String entryToDeleteAuthor = principal.getName();
        if (requestSenderAccountName.equals(entryToDeleteAuthor)) {
            entryService.deleteEntryById(entryId);
            LOG.info("Removing entry with provided ID " + entryId);
        } //TODO error handlng and delegation
    }

    //Update entry
    @PatchMapping(path = "/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEntry(@PathVariable Long entryId, @RequestBody EntryDTO entryDTO, Principal principal) {
        String requestSenderAccountName = principal.getName();
        Entry entryToUpdate = entryService.findEntryById(entryId);
        String entryToUpdateAuthor = entryToUpdate.getEntryAuthor();
        if (requestSenderAccountName.equals(entryToUpdateAuthor)) {
            entryToUpdate.setEntryText(entryDTO.getEntryText());
            entryService.saveEntry(entryToUpdate);
            LOG.info("Updating entry with id: " + entryId);
        } //TODO error handling and delegation
    }
}
