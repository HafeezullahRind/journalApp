package com.justcode.journalApp.repository;

import com.justcode.journalApp.entity.JournalEntry;
import com.justcode.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);


}
