package com.justcode.journalApp.service;

import com.justcode.journalApp.entity.User;
import com.justcode.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;


    public void save(User user) {

        try {
            userEntryRepository.save(user);

        } catch (Exception e) {
            // Handle the exception, e.g., log it
            System.err.println("Error : " + e.getMessage());
        }
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public User getById(ObjectId id) {
      return userEntryRepository.findById(id).orElse(null);
    }


    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }

}
