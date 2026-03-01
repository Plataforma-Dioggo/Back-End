package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.ObservacaoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoResponseDTO;
import com.example.plataformadioggoapi.model.Observacao;

import java.time.LocalDate;

public class ObservacaoMapper {
    public static Observacao toEntity(ObservacaoRequestDTO dto) {
        return Observacao.builder()
                .professorId(dto.getProfessorId())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .data(LocalDate.now())
                .build();
    }

    public static ObservacaoResponseDTO toResponseDTO(Observacao observacao) {
        return ObservacaoResponseDTO.builder()
                .professorId(observacao.getProfessorId())
                .titulo(observacao.getTitulo())
                .descricao(observacao.getDescricao())
                .data(observacao.getData())
                .build();
    }
}
