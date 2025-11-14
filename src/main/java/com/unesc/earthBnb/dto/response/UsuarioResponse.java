package com.unesc.earthBnb.dto.response;

import java.time.LocalDate;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String usuario,
        String telefone,
        LocalDate criadoEm
) {}