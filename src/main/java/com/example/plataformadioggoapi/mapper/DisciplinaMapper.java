package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    DisciplinaResponseDTO toDTO(Disciplina disciplina);

    List<DisciplinaResponseDTO> toDTOList(List<Disciplina> disciplinas);

    @Mapping(target = "id", ignore = true)
    Disciplina toEntity(DisciplinaRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(DisciplinaRequestDTO dto, @MappingTarget Disciplina entity);
}