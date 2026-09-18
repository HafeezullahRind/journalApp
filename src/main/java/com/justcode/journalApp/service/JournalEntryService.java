package com.justcode.journalApp.service;

import com.justcode.journalApp.entity.JournalEntry;
import com.justcode.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void save(JournalEntry journalEntry) {

        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);

        } catch (Exception e) {
            // Handle the exception, e.g., log it
            System.err.println("Error : " + e.getMessage());
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getById(ObjectId id) {
      return journalEntryRepository.findById(id).orElse(null);
    }


    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

}
