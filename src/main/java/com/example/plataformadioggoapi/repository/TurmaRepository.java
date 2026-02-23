package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Turma;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TurmaRepository extends MongoRepository<Turma, ObjectId>{
    Turma findByNome(String nome);
}
