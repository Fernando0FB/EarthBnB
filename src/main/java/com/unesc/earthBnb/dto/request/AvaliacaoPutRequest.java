package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AvaliacaoPutRequest(
        Integer nota,
        @Size(max = 80, message = "Comentário deve ter no máximo 80 caracteres")
        String comentario,
        LocalDateTime dataAvaliacao
) {
}

