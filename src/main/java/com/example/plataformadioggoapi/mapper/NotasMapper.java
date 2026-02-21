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
                .notaN1(nota.getNotaN1())
                .notaN2(nota.getNotaN2())
                .mediaN1(nota.getMediaN1())
                .mediaN2(nota.getMediaN2())
                .mediaFinal(nota.getMediaFinal())
                .build();
    }
}
