package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.LoginRequestDTO;
import com.example.plataformadioggoapi.dto.LoginResponseDTO;
import com.example.plataformadioggoapi.model.Aluno;
import com.example.plataformadioggoapi.model.Professor;
import com.example.plataformadioggoapi.repository.AlunoRepository;
import com.example.plataformadioggoapi.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO loginResponseDTO = null;

        Professor professor = professorRepository
                .findByUsuario(request.getUsuario())
                .orElse(null);

        if (professor != null && passwordEncoder.matches(request.getSenha(), professor.getSenha())) {
            loginResponseDTO = new LoginResponseDTO(
                    professor.getNome(),
                    professor.getUsuario(),
                    "PROFESSOR"
            );
        }

        Aluno aluno = alunoRepository
                .findByMatricula(request.getUsuario())
                .orElse(null);

        if (aluno != null && passwordEncoder.matches(request.getSenha(), aluno.getSenha())) {
            loginResponseDTO = new LoginResponseDTO(
                    aluno.getNome(),
                    aluno.getMatricula(),
                    "ALUNO"
            );
        }

        return loginResponseDTO;
    }
}
