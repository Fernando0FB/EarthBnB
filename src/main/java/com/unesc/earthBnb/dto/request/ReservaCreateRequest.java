package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservaCreateRequest(

        @NotNull(message = "O usuário é obrigatório")
        UsuarioPostRequest usuarioRequest,

        @NotNull(message = "A acomodação é obrigatório")
        AcomodacaoPostRequest acomodacaoPostRequest,

        @NotNull(message = "A data de check-in é obrigatória")
        LocalDate dataCheckin,

        @NotNull(message = "A data de check-out é obrigatória")
        LocalDate dataCheckout,

        @NotNull(message = "A quantidade de diárias é obrigatória")
        @Positive(message = "A quantidade de diárias deve ser maior que zero")
        Integer quantidadeDiarias,

        @NotNull(message = "O valor do pagamento é obrigatório")
        @Positive(message = "O valor do pagamento deve ser maior que zero")
        BigDecimal valorPagamento,

        @NotNull(message = "A data de reserva é obrigatória")
        LocalDateTime dataReserva
) {
}