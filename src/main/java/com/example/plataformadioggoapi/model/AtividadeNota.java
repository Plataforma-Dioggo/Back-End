package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AtividadeNota {
    private Double nota;
    private String nome_atividade;
    private String atribuicao;
}
