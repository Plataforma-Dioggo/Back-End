package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Observacao {
    private String professor_id;
    private String titulo;
    private String descricao;
    private LocalDate data;
}
