package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AvaliacaoPutRequest(
        @Min(value = 1, message = "A nota tem que ser igual ou maior que 1")
        @Max(value = 10, message = "A nota tem que ser igual ou menor que 10")
        Integer nota,
        @Size(max = 80, message = "Comentário deve ter no máximo 80 caracteres")
        String comentario
) {
}

