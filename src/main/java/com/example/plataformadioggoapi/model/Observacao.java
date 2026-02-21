package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Builder
@Data
public class Observacao {
    @Field("professor_id")
    private String professorId;
    private String titulo;
    private String descricao;
    private LocalDate data;
}
