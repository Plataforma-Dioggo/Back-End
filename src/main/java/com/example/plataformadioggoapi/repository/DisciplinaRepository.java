package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends MongoRepository<Disciplina, ObjectId> {

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'professores', localField: 'professor_id', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { " +
                    "id: { $toString: '$_id' }, " +
                    "professorId: { $toString: '$professor_id' }, " +
                    "professorNome: '$professorInfo.nome', " +
                    "professorUsuario: '$professorInfo.usuario' " +
                    "} }",
            "{ $project: { _id: 0, nome: 1, professorId: 1, professorNome: 1, professorUsuario: 1, id: 1 } }"
    })
    List<DisciplinaResponseDTO> listarDisciplinasComProfessor();

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'professores', localField: 'professor_id', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $addFields: { " +
                    "id: { $toString: '$_id' }, " +
                    "professorId: { $toString: '$professor_id' }, " +
                    "professorNome: '$professorInfo.nome', " +
                    "professorUsuario: '$professorInfo.usuario' " +
                    "} }",
            "{ $project: { _id: 0, nome: 1, professorId: 1, professorNome: 1, professorUsuario: 1, id: 1 } }"
    })
    Optional<DisciplinaResponseDTO> buscarDisciplinaComProfessor(ObjectId id);
}
