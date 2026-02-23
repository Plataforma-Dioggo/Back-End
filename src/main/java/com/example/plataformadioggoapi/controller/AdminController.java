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
    public ResponseEntity<Boolean> createAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(adminService.createAdmin(adminRequestDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Faz login de um admin")
    public ResponseEntity<Boolean> loginAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(adminService.loginAdmin(adminRequestDTO));
    }

    @PostMapping("/apagar")
    @Operation(summary = "Apaga um admin")
    public ResponseEntity<Boolean> apagarAdmin(@RequestParam String email) {
        return ResponseEntity.ok(adminService.apagarAdmin(email));
    }

    @PostMapping("/findAll")
    @Operation(summary = "Busca todos os admins")
    public ResponseEntity<List<AdminResponseDTO>> findAllAdmins() {
        return ResponseEntity.ok(adminService.findAllAdmins());
    }
}
