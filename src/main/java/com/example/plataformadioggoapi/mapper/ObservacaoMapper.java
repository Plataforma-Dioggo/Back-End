package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.ObservacaoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoResponseDTO;
import com.example.plataformadioggoapi.model.Observacao;

import java.time.LocalDate;

public class ObservacaoMapper {
    public static Observacao toEntity(ObservacaoRequestDTO dto, String professor_id) {
        return Observacao.builder()
                .professor_id(professor_id)
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .data(LocalDate.now())
                .build();
    }

    public static ObservacaoResponseDTO toResponseDTO(Observacao observacao) {
        return ObservacaoResponseDTO.builder()
                .professor_id(observacao.getProfessor_id())
                .titulo(observacao.getTitulo())
                .descricao(observacao.getDescricao())
                .data(observacao.getData())
                .build();
    }
}
