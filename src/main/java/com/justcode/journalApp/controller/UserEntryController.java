package com.justcode.journalApp.controller;



import com.justcode.journalApp.entity.User;
import com.justcode.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private  UserEntryService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAlljournalEntries() {
        List<User> users = userEntryService.getAll();
        if(users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userEntryService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
        User existingUser = userEntryService.findByUsername(username);
        if (existingUser != null) {
            // Update the existing user
            existingUser.setUsername(!user.getUsername().isEmpty() ? user.getUsername() : existingUser.getUsername());
            existingUser.setPassword(!user.getPassword().isEmpty() ? user.getPassword() : existingUser.getPassword());
            userEntryService.save(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 }





}