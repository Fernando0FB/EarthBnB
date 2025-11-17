package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.request.ReservaCreateRequest;
import com.unesc.earthBnb.dto.request.ReservaPutRequest;
import com.unesc.earthBnb.dto.response.ReservaResponse;
import com.unesc.earthBnb.model.Reservas;

import java.time.LocalDateTime;

public class ReservaMapper {

    public static ReservaResponse toResponse(Reservas reserva) {
        return new ReservaResponse(
            reserva.getId(),
            UsuarioMapper.toResponse(reserva.getUsuario()),
            AcomodacaoMapper.toResponse(reserva.getAcomodacoes()),
            reserva.getDataCheckin(),
            reserva.getDataCheckout(),
            reserva.getQuantidadeDiarias(),
            reserva.getValorPagamento(),
            reserva.getDataReserva()
        );
    }

    public static Reservas toEntity(ReservaCreateRequest reservaCreateRequest) {
        Reservas reserva = new Reservas();
        reserva.setDataCheckin(reservaCreateRequest.dataCheckin());
        reserva.setDataCheckout(reservaCreateRequest.dataCheckout());
        reserva.setDataReserva(LocalDateTime.now());
        return reserva;
    }

    public static Reservas merge(Reservas entity, ReservaPutRequest req) {
        if (req.dataCheckin() != null) entity.setDataCheckin(req.dataCheckin());
        if (req.dataCheckout() != null) entity.setDataCheckout(req.dataCheckout());
        return entity;
    }

}
