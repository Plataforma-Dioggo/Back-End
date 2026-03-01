package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.TurmaRequestDTO;
import com.example.plataformadioggoapi.dto.TurmaResponseDTO;
import com.example.plataformadioggoapi.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> adicionarAluno(
            @RequestParam String nomeTurma,
            @RequestParam String nomeAluno,
            @RequestParam String matriculaAluno) {

        turmaService.adicionarAluno(nomeTurma, nomeAluno, matriculaAluno);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remover-aluno")
    @Operation(summary = "Remover aluno da turma")
    public ResponseEntity<Void> removerAluno(
            @RequestParam String nomeTurma,
            @RequestParam String nomeAluno,
            @RequestParam String matriculaAluno) {

        turmaService.removerAluno(nomeTurma, nomeAluno, matriculaAluno);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Operation(summary = "Criar turma")
    public ResponseEntity<TurmaResponseDTO> criarTurma(
            @RequestBody TurmaRequestDTO turmaRequestDTO) {

        TurmaResponseDTO response = turmaService.criarTurma(turmaRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar turma pelo ID")
    public ResponseEntity<Void> apagarTurma(@PathVariable String id) {

        turmaService.apagarTurma(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-idProfessor")
    public ResponseEntity<List<TurmaResponseDTO>> listarIdProfessor(
            @RequestParam String idProfessor) {

        return ResponseEntity.ok(turmaService.listarIdProfessor(idProfessor));
    }

    @PatchMapping("/{nomeTurma}/alterarLiberacaoNotas")
    public ResponseEntity<Void> liberarNotas(@PathVariable String nomeTurma) {

        turmaService.alternarLiberacaoNotas(nomeTurma);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar turma totalmente pelo ID")
    public ResponseEntity<TurmaResponseDTO> atualizarTurma(
            @PathVariable String id,
            @RequestBody TurmaRequestDTO turmaRequestDTO) {

        TurmaResponseDTO response = turmaService.atualizarTurma(id, turmaRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar turma parcialmente pelo ID")
    public ResponseEntity<TurmaResponseDTO> atualizarTurmaParcial(
            @PathVariable String id,
            @RequestBody TurmaRequestDTO turmaRequestDTO) {

        TurmaResponseDTO response = turmaService.atualizarTurmaParcial(id, turmaRequestDTO);
        return ResponseEntity.ok(response);
    }
}