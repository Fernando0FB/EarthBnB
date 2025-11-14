package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Usuario é obrigatório") String usuario,
        @NotEmpty(message = "Senha é obrigatória") String senha
) {
}
