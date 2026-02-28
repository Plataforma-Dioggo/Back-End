package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.DisciplinaProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaProfessorMapper {


    List<DisciplinaProfessorResponseDTO> toDTOList(List<Disciplina> disciplinas);
}
