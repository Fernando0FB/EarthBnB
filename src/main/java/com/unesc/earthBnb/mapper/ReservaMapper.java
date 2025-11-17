package com.unesc.earthBnb.mapper;

import com.unesc.earthBnb.dto.request.ReservaCreateRequest;
import com.unesc.earthBnb.dto.request.ReservaPutRequest;
import com.unesc.earthBnb.dto.response.ReservaResponse;
import com.unesc.earthBnb.model.Reservas;

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
        reserva.setUsuario(UsuarioMapper.toEntity(reservaCreateRequest.usuarioRequest()));
        reserva.setAcomodacoes(AcomodacaoMapper.toEntity(reservaCreateRequest.acomodacaoPostRequest()));
        reserva.setDataCheckin(reservaCreateRequest.dataCheckin());
        reserva.setDataCheckout(reservaCreateRequest.dataCheckout());
        reserva.setQuantidadeDiarias(reservaCreateRequest.quantidadeDiarias());
        reserva.setValorPagamento(reservaCreateRequest.valorPagamento());
        reserva.setDataReserva(reservaCreateRequest.dataReserva());
        return reserva;
    }

    public static Reservas merge(Reservas entity, ReservaPutRequest req) {
        if (req.dataCheckin() != null) entity.setDataCheckin(req.dataCheckin());
        if (req.dataCheckout() != null) entity.setDataCheckout(req.dataCheckout());
        if (req.quantidadeDiarias() != null) entity.setQuantidadeDiarias(req.quantidadeDiarias());
        if (req.valorPagamento() != null) entity.setValorPagamento(req.valorPagamento());
        if (req.dataReserva() != null) entity.setDataReserva(req.dataReserva());
        return entity;
    }

}
