package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, ObjectId> {
    Admin findByEmail(String email);
}
