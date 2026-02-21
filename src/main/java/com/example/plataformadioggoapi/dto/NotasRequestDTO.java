package com.example.plataformadioggoapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotasRequestDTO {
    private List<AtividadeNotaRequestDTO> notas;
}
