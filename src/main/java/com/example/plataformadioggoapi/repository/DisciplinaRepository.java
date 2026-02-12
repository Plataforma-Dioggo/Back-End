package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Disciplina;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {

}
