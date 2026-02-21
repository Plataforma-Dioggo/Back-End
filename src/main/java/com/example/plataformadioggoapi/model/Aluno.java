package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Document(collection = "aluno")
@Data
public class Aluno {
    @Id
    private String id;
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private List<Observacao> observacoes;
    private String turma_id;
    private Boolean usou_sistema;
}
