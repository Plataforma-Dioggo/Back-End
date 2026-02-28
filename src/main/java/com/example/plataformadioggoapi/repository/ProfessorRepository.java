package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Professor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

    Optional<Professor> findByUsuario(String usuario);

    Optional<Professor> findById(ObjectId id);
}
