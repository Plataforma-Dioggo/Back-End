package com.example.plataformadioggoapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlunoResponseDTO {
    private String id;
    private String nome;
    private String matricula;
    private List<ObservacaoResponseDTO> observacoes;
    private String email;
    private Boolean usouSistema;
    private String turmaId;
}