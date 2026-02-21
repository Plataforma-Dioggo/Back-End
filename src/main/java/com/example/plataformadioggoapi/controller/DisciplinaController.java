package com.example.plataformadioggoapi.controller;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as disciplinas", description = "Retorna uma lista de todas as disciplinas cadastradas")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas() {
        List<DisciplinaResponseDTO> disciplinas = disciplinaService.listarDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Retorna uma disciplina específica pelo seu ID")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable String id) {

        DisciplinaResponseDTO disciplina = disciplinaService.buscarPorId(id);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova disciplina", description = "Cria uma nova disciplina com os dados fornecidos")
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@RequestBody DisciplinaRequestDTO disciplina) {
        DisciplinaResponseDTO novaDisciplina = disciplinaService.criarDisciplina(disciplina);
        return ResponseEntity.status(201).body(novaDisciplina);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente")
    public ResponseEntity<DisciplinaResponseDTO> atualizarDisciplina(
            @PathVariable String id,
            @RequestBody DisciplinaRequestDTO disciplina
    ) {

        DisciplinaResponseDTO disciplinaAtualizada =
                disciplinaService.atualizarDisciplina(id, disciplina);

        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @PatchMapping("/atualizar_parcial/{id}")
    @Operation(summary = "Atualizar parcialmente uma disciplina", description = "Atualiza apenas os campos fornecidos de uma disciplina existente")
    public ResponseEntity<DisciplinaResponseDTO> atualizarParcialDisciplina(
            @PathVariable String id,
            @RequestBody DisciplinaRequestDTO disciplina
    ) {

        if (!ObjectId.isValid(id)) {
            throw new RuntimeException("ID inválido.");
        }

        DisciplinaResponseDTO disciplinaAtualizada =
                disciplinaService.atualizarParcialDisciplina(id, disciplina);

        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar disciplina", description = "Remove uma disciplina existente pelo seu ID")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable String id) {


        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
