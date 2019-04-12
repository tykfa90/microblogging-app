package org.tykfa90.microbloggingapp.service;

import org.springframework.stereotype.Service;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.repository.EntryRepository;

import java.util.List;

@Service
public class EntryService {

    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    public Iterable<Entry> findAllEntriesByAccountId(Long id) {
        return entryRepository.findAllByAuthorId(id);
    }

    public Entry findEntryById(Long entryId) {
        return entryRepository.findById(entryId)
                .orElseThrow(()-> new IllegalArgumentException("No entry with this id found!"));
    }

    public void saveEntry(Entry entry) {
        entryRepository.save(entry);
    }

    public void deleteEntryById(Long entryId) {
        entryRepository.deleteById(entryId);
    }
}
