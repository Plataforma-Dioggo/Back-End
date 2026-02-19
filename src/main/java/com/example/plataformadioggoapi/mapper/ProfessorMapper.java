package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Professor;
import org.bson.types.ObjectId;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "id", expression = "java(objectIdToString(professor.getId()))")
    @Mapping(target = "disciplinaId", expression = "java(objectIdListToStringList(professor.getDisciplinaId()))")
    @Mapping(target = "nomeDisciplinas", ignore = true) // vem do aggregation
    ProfessorResponseDTO toDTO(Professor professor);

    List<ProfessorResponseDTO> toDTOList(List<Professor> professores);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disciplinaId", expression = "java(stringListToObjectIdList(dto.getDisciplinaId()))")
    Professor toEntity(ProfessorRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disciplinaId", expression = "java(stringListToObjectIdList(dto.getDisciplinaId()))")
    void updateEntityFromDTO(ProfessorRequestDTO dto, @MappingTarget Professor entity);

    // ==========================
    // Conversores auxiliares
    // ==========================

    default String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    default ObjectId stringToObjectId(String id) {
        if (id == null || id.isBlank()) return null;
        return new ObjectId(id);
    }

    default List<String> objectIdListToStringList(List<ObjectId> ids) {
        if (ids == null) return null;
        return ids.stream().map(this::objectIdToString).toList();
    }

    default List<ObjectId> stringListToObjectIdList(List<String> ids) {
        if (ids == null) return null;
        return ids.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(ObjectId::new)
                .toList();
    }
}
