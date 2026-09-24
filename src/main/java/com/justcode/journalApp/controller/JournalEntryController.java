package com.justcode.journalApp.controller;


import com.justcode.journalApp.entity.JournalEntry;
import com.justcode.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> entries = journalEntryService.getAll();
        if(entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
     try {
         entry.setDate(LocalDateTime.now());
         journalEntryService.save(entry);
         return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception, e.g., log it
            System.err.println("Error : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {

       Optional<JournalEntry> entry = Optional.ofNullable(journalEntryService.getById(myId));
        if (!entry.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entry.get(), HttpStatus.OK);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry entry) {
        JournalEntry existingEntry = journalEntryService.getById(myId);

        if(existingEntry != null) {
            existingEntry.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : existingEntry.getTitle());
            existingEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : existingEntry.getContent());
            journalEntryService.save(existingEntry);
            return new ResponseEntity<>(existingEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
     journalEntryService.deleteById(myId);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}