package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.AtividadeNotaRequestDTO;
import com.example.plataformadioggoapi.dto.AtividadeNotaResponseDTO;
import com.example.plataformadioggoapi.model.AtividadeNota;

public class AtividadeNotaMapper {
    public static AtividadeNota toEntity(AtividadeNotaRequestDTO atividadeNotaRequestDTO) {
        return AtividadeNota.builder()
                .nota(atividadeNotaRequestDTO.getNota())
                .nomeAtividade(atividadeNotaRequestDTO.getNomeAtividade())
                .atribuicao(atividadeNotaRequestDTO.getAtribuicao())
                .build();
    }

    public static AtividadeNotaResponseDTO toResponseDTO(AtividadeNota atividadeNota) {
        return AtividadeNotaResponseDTO.builder()
                .nota(atividadeNota.getNota())
                .nomeAtividade(atividadeNota.getNomeAtividade())
                .atribuicao(atividadeNota.getAtribuicao())
                .build();
    }
}
