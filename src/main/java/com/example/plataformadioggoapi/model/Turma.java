package com.example.plataformadioggoapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Turma {

    @Id
    @Field("_id")
    private String id;
    private String nome;
    private List<AlunoTurma> alunos;
    private Boolean liberarNotas;
    private List<String> diciplina_id;

}
