package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.TurmaRequestDTO;
import com.example.plataformadioggoapi.dto.TurmaResponseDTO;
import com.example.plataformadioggoapi.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/turmas")
@Tag(name = "Turmas", description = "Gerenciamento de Turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as turmas")
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @PostMapping("/adicionar-aluno")
    @Operation(summary = "Adicionar aluno a turma")
    public ResponseEntity<Boolean> adicionarAluno(String nomeTurma, String nomeAluno, String matriculaAluno) {
        Boolean response = turmaService.adicionarAluno(nomeTurma, nomeAluno, matriculaAluno);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/remover-aluno")
    @Operation(summary = "Remover aluno da turma")
    public ResponseEntity<Boolean> removerAluno(String nomeTurma, String nomeAluno, String matriculaAluno){
        Boolean response = turmaService.removerAluno(nomeTurma, nomeAluno, matriculaAluno);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @Operation(summary = "Criar turma")
    public ResponseEntity<Boolean> criarTurma(@RequestBody TurmaRequestDTO turmaRequestDTO){
        Boolean response = turmaService.criarTurma(turmaRequestDTO);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Apagar turma")
    public ResponseEntity<Boolean> apagarTurma(String nomeTurma){
        Boolean response = turmaService.apagarTurma(nomeTurma);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
