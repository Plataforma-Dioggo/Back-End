package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Professor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, ObjectId> {

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'disciplinas', localField: 'disciplinas', foreignField: '_id', as: 'disciplinasInfo' } }",
            "{ $addFields: { nomeDisciplinas: { $map: { input: '$disciplinasInfo', as: 'd', in: '$$d.nome' } } } }",
            "{ $project: { nome: 1, usuario: 1, senha: 1, nomeDisciplinas: 1 } }"
    })
    List<ProfessorResponseDTO> listarProfessoresComNomeDisciplinas();

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'disciplinas', localField: 'disciplinas', foreignField: '_id', as: 'disciplinasInfo' } }",
            "{ $addFields: { nomeDisciplinas: { $map: { input: '$disciplinasInfo', as: 'd', in: '$$d.nome' } } } }",
            "{ $project: { nome: 1, usuario: 1, senha: 1, nomeDisciplinas: 1 } }"
    })
    Optional<ProfessorResponseDTO> buscarProfessorComNomeDisciplinas(ObjectId id);

}