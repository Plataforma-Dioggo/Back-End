package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.AdminRequestDTO;
import com.example.plataformadioggoapi.dto.AdminResponseDTO;
import com.example.plataformadioggoapi.mapper.AdminMapper;
import com.example.plataformadioggoapi.model.Admin;
import com.example.plataformadioggoapi.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public boolean loginAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = adminRepository.findByEmail(adminRequestDTO.getEmail());
        if (admin == null) {
            return false;
        }
        return admin.getSenha().equals(adminRequestDTO.getSenha());
    }

    public Boolean createAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = adminMapper.toModel(adminRequestDTO);
        adminRepository.save(admin);
        return true;
    }

    public Boolean apagarAdmin(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            return false;
        }
        adminRepository.delete(admin);
        return true;
    }

    public List<AdminResponseDTO> findAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(adminMapper::toResponseDTO).collect(Collectors.toList());
    }
}
