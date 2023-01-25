package com.bloggers.blog.controller;

import com.bloggers.blog.model.Entry;
import com.bloggers.blog.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EntryController {
    @Autowired
    EntryRepository entryRepository;

    @GetMapping("/test")
    public String testApi() {
        return "test";
    }

    @GetMapping("/entries")
    public ResponseEntity<List<Entry>> getAllEntries() {
        try {
            List<Entry> entries = new ArrayList<>(entryRepository.findAll());
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/entry")
    public ResponseEntity<Entry> getEntryById(@RequestParam long id) {
        try {
            Optional<Entry> optionalEntry = entryRepository.findById(id);
            Entry entry;
            if (optionalEntry.isPresent()) {
                entry = optionalEntry.get();
                return new ResponseEntity<>(entry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "/entry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entry> postEntry(@RequestBody Entry entry) {
        try {
            Entry createdEntry = entryRepository.save(new Entry(entry.getTitle(), entry.getCreationDate(), entry.getText()));
            return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/entry")
    public ResponseEntity<Entry> putEntry(@RequestBody Entry entry) {
        try {
            Optional<Entry> optionalEntry = entryRepository.findById(entry.getId());
            Entry updatedEntry;
            if (optionalEntry.isPresent()) {
                updatedEntry = entryRepository.save(new Entry(entry.getId(), entry.getTitle(), entry.getCreationDate(), entry.getText()));
                return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/entry")
    public ResponseEntity<String> deleteEntry(@RequestBody Entry entry) {
        try {
            Optional<Entry> optionalEntry = entryRepository.findById(entry.getId());
            optionalEntry.ifPresent(originalEntry -> entryRepository.deleteById(originalEntry.getId()));
            return new ResponseEntity<>("The entry has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
