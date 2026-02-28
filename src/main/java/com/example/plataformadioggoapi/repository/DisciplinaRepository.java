package com.example.plataformadioggoapi.repository;

import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'professores', localField: 'professor_id', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $project: { " +
                    "\"id\": { \"$toString\": \"$_id\" }, " +
                    "\"nome\": 1, " +
                    "\"professor_id_string\": { \"$toString\": \"$professorInfo._id\" }, " +
                    "\"professorNome\": \"$professorInfo.nome\", " +
                    "\"professorUsuario\": \"$professorInfo.usuario\" " +
                    "} }"
    })
    List<DisciplinaResponseDTO> listarDisciplinasComProfessor();

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'professores', localField: 'professor_id', foreignField: '_id', as: 'professorInfo' } }",
            "{ $unwind: { path: '$professorInfo', preserveNullAndEmptyArrays: true } }",
            "{ $project: { " +
                    "\"id\": { \"$toString\": \"$_id\" }, " +
                    "\"nome\": 1, " +
                    "\"professor_id_string\": { \"$toString\": \"$professorInfo._id\" }, " +
                    "\"professorNome\": \"$professorInfo.nome\", " +
                    "\"professorUsuario\": \"$professorInfo.usuario\" " +
                    "} }"
    })
    Optional<DisciplinaResponseDTO> buscarDisciplinaComProfessor(ObjectId id);

    @Query("{ 'professor_id': ?0 }")
    List<Disciplina> findDisciplinaByProfessorId(ObjectId professorId);
  
    List<DisciplinaResponseDTO> findByProfessorId(String id);
}
