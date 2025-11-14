package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Usuário é obrigatório")
        @Size(max = 80, message = "Usuário deve ter no máximo 80 caracteres")
        String usuario,

        @NotBlank(message = "Senha é obrigatória")
        @Size(max = 80, message = "Senha deve ter no máximo 80 caracteres")
        String senha,

        @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
        String telefone,

        Role role
) {}