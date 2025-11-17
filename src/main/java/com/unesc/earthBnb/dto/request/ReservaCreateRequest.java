package com.unesc.earthBnb.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaCreateRequest(

        @NotNull(message = "O usuário é obrigatório")
        UsuarioRequest usuario,

        @NotNull(message = "A acomodação é obrigatório")
        AcomodacaoRequest acomodacao,

        @NotNull(message = "A data de check-in é obrigatória")
        LocalDate dataCheckin,

        @NotNull(message = "A data de check-out é obrigatória")
        LocalDate dataCheckout
) {
    public record UsuarioRequest(
            @NotNull(message = "O ID do usuário é obrigatório")
            Long id
    ) {

        public Long getId() {
            return id;
        }
    }

    public record AcomodacaoRequest(
            @NotNull(message = "O ID da acomodação é obrigatório")
            Long id
    ) {

        public Long getId() {
            return id;
        }
    }
}