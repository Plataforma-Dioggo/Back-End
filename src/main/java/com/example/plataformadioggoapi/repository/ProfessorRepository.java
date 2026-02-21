package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Professor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'disciplinas', localField: 'disciplina_id', foreignField: '_id', as: 'disciplinasInfo' } }",
            "{ $addFields: { " +
                    "id: { $toString: '$_id' }, " +
                    "disciplinaId: { $map: { input: '$disciplina_id', as: 'd', in: { $toString: '$$d' } } }, " +
                    "nomeDisciplinas: { $map: { input: '$disciplinasInfo', as: 'd', in: '$$d.nome' } } " +
                    "} }",
            "{ $project: { _id: 0, nome: 1, usuario: 1, disciplinaId: 1, nomeDisciplinas: 1 } }"
    })
    List<ProfessorResponseDTO> listarProfessoresComNomeDisciplinas();

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'disciplinas', localField: 'disciplina_id', foreignField: '_id', as: 'disciplinasInfo' } }",
            "{ $addFields: { " +
                    "id: { $toString: '$_id' }, " +
                    "disciplinaId: { $map: { input: '$disciplina_id', as: 'd', in: { $toString: '$$d' } } }, " +
                    "nomeDisciplinas: { $map: { input: '$disciplinasInfo', as: 'd', in: '$$d.nome' } } " +
                    "} }",
            "{ $project: { _id: 0, nome: 1, usuario: 1, disciplinaId: 1, nomeDisciplinas: 1 } }"
    })
    Optional<ProfessorResponseDTO> buscarProfessorComNomeDisciplinas(String id);
}
