package com.unesc.earthBnb.dto.request;

import com.unesc.earthBnb.model.Reservas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AvaliacaoPostRequest(
        @NotBlank(message = "Reserva é obrigatório")
        Reservas reserva,

        @NotBlank(message = "Nota é obrigatório")
        Integer nota,

        @Size(max = 80, message = "Comentário deve ter no máximo 80 caracteres")
        String comentario,

        @NotBlank(message = "Data é obrigatório")
        LocalDateTime dataAvaliacao
) {
}

