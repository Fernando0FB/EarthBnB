package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.ReservaCreateRequest;
import com.unesc.earthBnb.dto.request.ReservaPutRequest;
import com.unesc.earthBnb.dto.response.ReservaResponse;
import com.unesc.earthBnb.exception.*;
import com.unesc.earthBnb.mapper.ReservaMapper;
import com.unesc.earthBnb.model.Acomodacoes;
import com.unesc.earthBnb.model.Reservas;
import com.unesc.earthBnb.repository.AcomodacaoRepository;
import com.unesc.earthBnb.repository.ReservaRepository;
import com.unesc.earthBnb.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final AcomodacaoRepository acomodacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, AcomodacaoRepository acomodacaoRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.acomodacaoRepository = acomodacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public ReservaResponse getReservaById(Long id) {
        Reservas reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException(id));

        return ReservaMapper.toResponse(reserva);
    }

    @Transactional(readOnly = true)
    public List<ReservaResponse> getAllReservas() {
        List<Reservas> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(ReservaMapper::toResponse)
                .toList();
    }

    @Transactional
    public ReservaResponse createReserva(ReservaCreateRequest reservaCreateRequest) {
        Reservas reserva = ReservaMapper.toEntity(reservaCreateRequest);

        Long idAcomodacao = reservaCreateRequest.acomodacao().getId();
        Acomodacoes acomodacoes = acomodacaoRepository.findById(idAcomodacao).orElseThrow(
                () -> new AcomodacaoNaoEncontradaException(idAcomodacao)
        );
        reserva.setAcomodacoes(acomodacoes);

        Long idUsuario = reservaCreateRequest.usuario().getId();
        reserva.setUsuario(
                usuarioRepository.findById(idUsuario).orElseThrow(
                        () -> new UsuarioNaoEncontradoException(idUsuario)
                )
        );

        LocalDate dataCheckin = reserva.getDataCheckin();
        LocalDate dataCheckout = reserva.getDataCheckout();
        BigDecimal precoDiaria = acomodacoes.getPrecoDiaria();

        checkConcomitanciaEValidaData(dataCheckin, dataCheckout, null, reserva.getAcomodacoes().getId());
        updateQuantidadeDiariasAndValorPagamento(dataCheckout, dataCheckin, reserva, precoDiaria);

        return ReservaMapper.toResponse(reservaRepository.save(reserva));
    }

    @Transactional
    public ReservaResponse updateReserva(Long id, ReservaPutRequest reservaPutRequest) {
        Reservas existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException(id));

        existingReserva = ReservaMapper.merge(existingReserva, reservaPutRequest);

        checkConcomitanciaEValidaData(
                existingReserva.getDataCheckin(),
                existingReserva.getDataCheckout(),
                existingReserva.getId(),
                existingReserva.getAcomodacoes().getId()
        );

        updateQuantidadeDiariasAndValorPagamento(
                existingReserva.getDataCheckout(),
                existingReserva.getDataCheckin(),
                existingReserva,
                existingReserva.getAcomodacoes().getPrecoDiaria()
        );

        existingReserva = reservaRepository.save(existingReserva);
        return ReservaMapper.toResponse(existingReserva);
    }

    @Transactional
    public void deleteReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNaoEncontradaException(id);
        }
        reservaRepository.deleteById(id);
    }

    private void checkConcomitanciaEValidaData(LocalDate dataCheckin, LocalDate dataCheckout, Long idReserva, Long idAcomodacao) {
        if (dataCheckin.isAfter(dataCheckout)) {
            throw new DataCheckinInvalidaException();
        }

        List<Reservas> confiltos = reservaRepository.findConflitos(
                idAcomodacao,
                dataCheckin,
                dataCheckout
        ).stream().filter(reserva -> idReserva == null || !reserva.getId().equals(idReserva)).toList();
        if (!confiltos.isEmpty()) {
            throw new ExisteReservasConcomitantesException();
        }
    }

    private static void updateQuantidadeDiariasAndValorPagamento(LocalDate dataCheckout, LocalDate dataCheckin, Reservas reserva, BigDecimal precoDiaria) {
        Integer diferencaDias = (int) (dataCheckout.toEpochDay() - dataCheckin.toEpochDay() + 1);
        reserva.setQuantidadeDiarias(diferencaDias);

        reserva.setValorPagamento(BigDecimal.valueOf(diferencaDias)
                .multiply(precoDiaria)
                .setScale(2)
        );
    }
}
