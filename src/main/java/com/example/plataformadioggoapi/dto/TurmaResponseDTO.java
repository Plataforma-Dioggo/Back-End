package com.example.plataformadioggoapi.dto;

import com.example.plataformadioggoapi.model.AlunoTurma;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@Data
public class TurmaResponseDTO {
    private String id;
    private String nome;
    private List<AlunoTurma> alunos;
    private Boolean liberarNotas;
    private List<String> diciplina_id;
}
