package com.example.plataformadioggoapi.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "professores")
public class Professor {

    @Id
    @Field("_id")
    private ObjectId id;

    private String nome;
    private String usuario;
    private String senha;

    @Field("disciplina_id")
    private List<ObjectId> disciplinaId;
}
