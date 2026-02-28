package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Turma;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends MongoRepository<Turma, String>{
    Turma findByNome(String nome);

    Optional<Turma> findById(String id);
}
