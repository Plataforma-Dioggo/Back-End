package com.example.plataformadioggoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class AlunoTurma {
    private String nome;
    private String matricula;
}
