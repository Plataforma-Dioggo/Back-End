package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.AtividadeNotaResponseDTO;
import com.example.plataformadioggoapi.dto.NotasRequestDTO;
import com.example.plataformadioggoapi.dto.NotasResponseDTO;
import com.example.plataformadioggoapi.model.AtividadeNota;
import com.example.plataformadioggoapi.model.Notas;

import java.util.List;

public class NotasMapper {
    public static Notas toEntity(NotasRequestDTO dto) {

        List<AtividadeNota> atividades = dto.getNotas()
                .stream()
                .map(AtividadeNotaMapper::toEntity)
                .toList();

        return Notas.builder()
                .notas(atividades)
                .build();
    }

    public static NotasResponseDTO toResponse(Notas nota) {

        List<AtividadeNotaResponseDTO> atividades = nota.getNotas()
                .stream()
                .map(AtividadeNotaMapper::toResponseDTO)
                .toList();

        return NotasResponseDTO.builder()
                .atividades(atividades)
                .nota_n1(nota.getNota_n1())
                .nota_n2(nota.getNota_n2())
                .media_n1(nota.getMedia_n1())
                .media_n2(nota.getMedia_n2())
                .media_final(nota.getMedia_final())
                .build();
    }
}
