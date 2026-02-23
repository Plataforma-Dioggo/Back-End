package com.example.plataformadioggoapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
public class AtividadeNota {
    private Double nota;
    @Field("nome_atividade")
    private String nomeAtividade;
    private String atribuicao;
}
