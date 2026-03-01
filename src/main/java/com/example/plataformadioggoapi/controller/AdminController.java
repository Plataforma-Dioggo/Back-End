package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.AdminRequestDTO;
import com.example.plataformadioggoapi.dto.AdminResponseDTO;
import com.example.plataformadioggoapi.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Admin", description = "Gerenciamento de Admin")
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @Operation(summary = "Cria um admin")
    public ResponseEntity<AdminResponseDTO> createAdmin(
            @RequestBody AdminRequestDTO adminRequestDTO) {

        AdminResponseDTO response = adminService.createAdmin(adminRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Faz login de um admin")
    public ResponseEntity<Void> loginAdmin(
            @RequestBody AdminRequestDTO adminRequestDTO) {

        adminService.loginAdmin(adminRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Apaga um admin")
    public ResponseEntity<Void> apagarAdmin(
            @RequestParam String email) {

        adminService.apagarAdmin(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Busca todos os admins")
    public ResponseEntity<List<AdminResponseDTO>> findAllAdmins() {

        return ResponseEntity.ok(adminService.findAllAdmins());
    }
}