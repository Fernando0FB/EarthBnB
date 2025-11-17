package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioPutRequest(
        @Size(max = 80, message = "Nome deve ter no máximo 80 caracteres")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @Size(max = 80, message = "Usuário deve ter no máximo 80 caracteres")
        String usuario,

        @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
        String telefone,

        Role role
) {}