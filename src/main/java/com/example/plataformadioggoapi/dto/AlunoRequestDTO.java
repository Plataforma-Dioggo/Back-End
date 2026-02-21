package com.example.plataformadioggoapi.dto;

import lombok.Data;

@Data
public class AlunoRequestDTO {
    private String nome;
    private String matricula;
    private String email;
    private String senha;
}
