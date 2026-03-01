package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.dto.DisciplinaProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {

    @Aggregation(pipeline = {
            "{ $addFields: { professorIdObj: { $toObjectId: '$professor_id' } } }",
            "{ $lookup: { from: 'professores', localField: 'professorIdObj', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $project: { " +
                    "id: '$_id', " +
                    "nome: 1, " +
                    "professorId: '$professor_id', " +
                    "professorNome: '$professorInfo.nome', " +
                    "professorUsuario:  '$professorInfo.usuario' " +
                    "} }"
    })
    List<DisciplinaProfessorResponseDTO> listarDisciplinasComProfessor();

    @Aggregation(pipeline = {
            "{ $match: { _id: { $toObjectId: ?0 } } }",
            "{ $addFields: { professorIdObj: { $toObjectId: '$professor_id' } } }",
            "{ $lookup: { from: 'professores', localField: 'professorIdObj', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $project: { " +
                    "id: { $toString: '$_id' }, " +
                    "nome: 1, " +
                    "professorId: '$professor_id', " +
                    "professorNome: '$professorInfo.nome', " +
                    "professorUsuario: '$professorInfo.usuario' " +
                    "} }"
    })
    Optional<DisciplinaProfessorResponseDTO> buscarDisciplinaComProfessor(String id);

    List<DisciplinaProfessorResponseDTO> findByProfessorId(String professorId);

    List<Disciplina> findDisciplinaByProfessorId(String professorId);
}