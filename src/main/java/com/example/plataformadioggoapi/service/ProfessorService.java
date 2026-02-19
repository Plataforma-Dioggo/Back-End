package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.mapper.ProfessorMapper;
import com.example.plataformadioggoapi.model.Professor;
import com.example.plataformadioggoapi.repository.ProfessorRepository;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(
            ProfessorRepository professorRepository,
            ProfessorMapper professorMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ProfessorResponseDTO> listarProfessores() {
        return professorRepository.listarProfessoresComNomeDisciplinas();
    }

    public ProfessorResponseDTO buscarPorId(ObjectId id) {
        return professorRepository.buscarProfessorComNomeDisciplinas(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));
    }

    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto) {

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (dto.getUsuario() == null || dto.getUsuario().isBlank()) {
            throw new RuntimeException("O usuário é obrigatório.");
        }

        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            throw new RuntimeException("A senha é obrigatória.");
        }

        // valida lista de disciplinas
        if (dto.getDisciplinaId() != null) {
            for (String id : dto.getDisciplinaId()) {
                if (!ObjectId.isValid(id)) {
                    throw new RuntimeException("DisciplinaId inválido: " + id);
                }
            }
        }

        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Professor novo = professorMapper.toEntity(dto);

        Professor salvo = professorRepository.save(novo);

        // retorna com nomeDisciplinas preenchido
        return buscarPorId(salvo.getId());
    }

    public ProfessorResponseDTO atualizarProfessor(ObjectId id, ProfessorRequestDTO dto) {

        Professor existente = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        } else {
            dto.setSenha(existente.getSenha());
        }

        if (dto.getDisciplinaId() != null) {
            for (String discId : dto.getDisciplinaId()) {
                if (!ObjectId.isValid(discId)) {
                    throw new RuntimeException("DisciplinaId inválido: " + discId);
                }
            }
        }

        professorMapper.updateEntityFromDTO(dto, existente);

        Professor salvo = professorRepository.save(existente);

        return buscarPorId(salvo.getId());
    }

    public ProfessorResponseDTO atualizarParcialProfessor(ObjectId id, ProfessorRequestDTO dto) {

        Professor existente = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            existente.setNome(dto.getNome());
        }

        if (dto.getUsuario() != null && !dto.getUsuario().isBlank()) {
            existente.setUsuario(dto.getUsuario());
        }

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        if (dto.getDisciplinaId() != null && !dto.getDisciplinaId().isEmpty()) {

            for (String discId : dto.getDisciplinaId()) {
                if (!ObjectId.isValid(discId)) {
                    throw new RuntimeException("DisciplinaId inválido: " + discId);
                }
            }

            existente.setDisciplinaId(
                    dto.getDisciplinaId().stream().map(ObjectId::new).toList()
            );
        }

        Professor salvo = professorRepository.save(existente);

        return buscarPorId(salvo.getId());
    }

    public void deletarProfessor(ObjectId id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor de ID " + id + " não encontrado.");
        }
        professorRepository.deleteById(id);
    }
}
