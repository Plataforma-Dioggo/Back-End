package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@Tag(name = "Professores", description = "Gerenciamento de professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista de todos os professores cadastrados")
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores() {
        List<ProfessorResponseDTO> professores = professorService.listarProfessores();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor específico pelo seu ID")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable ObjectId id) {
        ProfessorResponseDTO professor = professorService.buscarPorId(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo professor", description = "Cria um novo professor com os dados fornecidos")
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@RequestBody ProfessorRequestDTO professor) {
        ProfessorResponseDTO novo = professorService.criarProfessor(professor);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable ObjectId id,
            @RequestBody ProfessorRequestDTO professor
    ) {
        ProfessorResponseDTO atualizado = professorService.atualizarProfessor(id, professor);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/atualizar_parcial/{id}")
    @Operation(summary = "Atualizar parcialmente um professor", description = "Atualiza apenas os campos fornecidos de um professor existente")
    public ResponseEntity<ProfessorResponseDTO> atualizarParcialProfessor(
            @PathVariable ObjectId id,
            @RequestBody ProfessorRequestDTO professor
    ) {
        ProfessorResponseDTO atualizado = professorService.atualizarParcialProfessor(id, professor);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar professor", description = "Remove um professor existente pelo seu ID")
    public ResponseEntity<Void> deletarProfessor(@PathVariable ObjectId id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
