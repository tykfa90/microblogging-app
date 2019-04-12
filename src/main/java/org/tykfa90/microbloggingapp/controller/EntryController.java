package org.tykfa90.microbloggingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.dto.EntryDTO;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.service.EntryService;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/entries")
public class EntryController {

    private Logger LOG = Logger.getLogger(EntryController.class.getName());

    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
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
    public void addNewEntry(@RequestBody EntryDTO entryDto) {
        Entry entry = new Entry();
        entry.setAccountId(entryDto.getAccountId());
        entry.setEntryText(entryDto.getEntryText());
        entryService.saveEntry(entry);

        LOG.info("Added new entry");
    }

    //Delete entry
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeEntry(@RequestBody Long entryId) {
        entryService.deleteEntryById(entryId);
        LOG.info("Removing entry with provided ID " + entryId);
    }
}
