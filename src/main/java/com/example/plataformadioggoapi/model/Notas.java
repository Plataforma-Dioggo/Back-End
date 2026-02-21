package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Document(collection = "notas")
@Data
public class Notas {
    @Id
    private String id;
    private List<AtividadeNota> notas;
    private List<Double> nota_n1;
    private List<Double> nota_n2;
    private Double media_n1;
    private Double media_n2;
    private Double media_final;
    private String matricula_nota;
    private String disciplina_id;
}
