package com.example.plataformadioggoapi.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "disciplinas")
public class Disciplina {

    @Id
    @Field("_id")
    private String id;

    private String nome;

    @Field("professor_id")
    private String professorId;
}