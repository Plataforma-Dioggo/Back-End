package com.example.plataformadioggoapi.dto;

import com.example.plataformadioggoapi.model.AlunoTurma;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class TurmaRequestDTO {
    private String nome;
    private List<AlunoTurma> alunos;
    private List<String> diciplinaId;
}
