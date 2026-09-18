package com.justcode.journalApp.controller;


import com.justcode.journalApp.entity.JournalEntry;
import com.justcode.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return  journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalEntryService.save(entry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId) {
        return journalEntryService.getById(myId);
    }

    @PutMapping("id/{myId}")
    public boolean updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry entry) {
        JournalEntry existingEntry = journalEntryService.getById(myId);

        if(existingEntry != null) {
            existingEntry.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : existingEntry.getTitle());
            existingEntry.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : existingEntry.getContent());
            journalEntryService.save(existingEntry);
            return true;
        }
        return false;
    }



    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId) {
     journalEntryService.deleteById(myId);
     return true;
    }



}