package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Disciplina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisciplinaRepository extends MongoRepository<Disciplina, ObjectId> {

}
