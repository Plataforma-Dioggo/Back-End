package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document(collection = "aluno")
@Data
public class Aluno {
    @Id
    @Field("_id")
    private String id;
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private List<Observacao> observacoes;
    @Field("turma_id")
    private String turma_id;
    @Field("usou_sistema")
    private Boolean usou_sistema;
}
