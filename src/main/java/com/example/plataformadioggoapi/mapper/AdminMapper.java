package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.AdminRequestDTO;
import com.example.plataformadioggoapi.dto.AdminResponseDTO;
import com.example.plataformadioggoapi.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminResponseDTO toResponseDTO(Admin admin);
    Admin toModel(AdminRequestDTO adminRequestDTO);
}
