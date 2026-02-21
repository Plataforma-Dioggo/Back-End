package com.example.plataformadioggoapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponseDTO {

    private String nome;
    private String usuario;
    private String tipo;
}
