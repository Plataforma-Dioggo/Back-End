package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
    Optional<Aluno> findByMatricula(String matricula);
}
