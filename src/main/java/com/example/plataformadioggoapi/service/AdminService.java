package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.AdminRequestDTO;
import com.example.plataformadioggoapi.dto.AdminResponseDTO;
import com.example.plataformadioggoapi.exception.BadRequestException;
import com.example.plataformadioggoapi.exception.EntityNotFoundException;
import com.example.plataformadioggoapi.exception.UnauthorizedException;
import com.example.plataformadioggoapi.mapper.AdminMapper;
import com.example.plataformadioggoapi.model.Admin;
import com.example.plataformadioggoapi.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminService(
            AdminRepository adminRepository,
            AdminMapper adminMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void loginAdmin(AdminRequestDTO adminRequestDTO) {

        if (adminRequestDTO.getEmail() == null || adminRequestDTO.getEmail().isBlank()) {
            throw new BadRequestException("Email é obrigatório.");
        }

        if (adminRequestDTO.getSenha() == null || adminRequestDTO.getSenha().isBlank()) {
            throw new BadRequestException("Senha é obrigatória.");
        }

        Admin admin = adminRepository.findByEmail(adminRequestDTO.getEmail());

        if (admin == null ||
                !passwordEncoder.matches(adminRequestDTO.getSenha(), admin.getSenha())) {

            throw new UnauthorizedException("Email ou senha inválidos.");
        }
    }

    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {

        if (adminRequestDTO.getEmail() == null || adminRequestDTO.getEmail().isBlank()) {
            throw new BadRequestException("Email é obrigatório.");
        }

        if (adminRequestDTO.getSenha() == null || adminRequestDTO.getSenha().isBlank()) {
            throw new BadRequestException("Senha é obrigatória.");
        }

        Admin admin = adminMapper.toModel(adminRequestDTO);

        // 🔐 Hash da senha antes de salvar
        admin.setSenha(passwordEncoder.encode(adminRequestDTO.getSenha()));

        Admin salvo = adminRepository.save(admin);

        return adminMapper.toResponseDTO(salvo);
    }

    public void apagarAdmin(String email) {

        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email é obrigatório.");
        }

        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) {
            throw new EntityNotFoundException("Admin não encontrado.");
        }

        adminRepository.delete(admin);
    }

    public List<AdminResponseDTO> findAllAdmins() {

        List<Admin> admins = adminRepository.findAll();

        return admins.stream()
                .map(adminMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}