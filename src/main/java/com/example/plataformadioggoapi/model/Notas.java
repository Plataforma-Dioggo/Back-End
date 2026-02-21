package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Document(collection = "notas")
@Data
public class Notas {
    @Id
    @Field("_id")
    private String id;
    private List<AtividadeNota> notas;
    @Field("nota_n1")
    private List<Double> notaN1;
    @Field("nota_n2")
    private List<Double> notaN2;
    @Field("media_n1")
    private Double mediaN1;
    @Field("media_n2")
    private Double mediaN2;
    @Field("media_final")
    private Double mediaFinal;
    @Field("matricula_nota")
    private String matriculaNota;
    @Field("disciplina_id")
    private String disciplinaId;
}
