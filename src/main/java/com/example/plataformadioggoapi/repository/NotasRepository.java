package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.model.Notas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NotasRepository extends MongoRepository<Notas, String> {
    List<Notas> findByMatricula(String matricula);

    Optional<Notas> findByMatriculaAndDisciplinaId(String matricula, String disciplinaId);
}
