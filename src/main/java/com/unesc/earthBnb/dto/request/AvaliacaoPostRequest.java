package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public record AvaliacaoPostRequest(
        @NotNull(message = "Reserva é obrigatório")
        ReservaRequest reserva,

        @NotNull(message = "Nota é obrigatório")
        @Min(value = 1, message = "A nota tem que ser igual ou maior que 1")
        @Max(value = 10, message = "A nota tem que ser igual ou menor que 10")
        Integer nota,

        @Size(max = 80, message = "Comentário deve ter no máximo 80 caracteres")
        String comentario
) {
    public record ReservaRequest(
            @NotNull(message = "Id da reserva é obrigatório")
            Long id
    ) {
        public Long getId() {
            return id;
        }
    }
}

