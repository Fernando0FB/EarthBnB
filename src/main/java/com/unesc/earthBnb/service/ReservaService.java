package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.ReservaCreateRequest;
import com.unesc.earthBnb.dto.request.ReservaPutRequest;
import com.unesc.earthBnb.dto.response.ReservaResponse;
import com.unesc.earthBnb.exception.ReservaNaoEncontradaException;
import com.unesc.earthBnb.mapper.ReservaMapper;
import com.unesc.earthBnb.model.Reservas;
import com.unesc.earthBnb.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
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
        reserva = reservaRepository.save(reserva);
        return ReservaMapper.toResponse(reserva);
    }

    @Transactional
    public ReservaResponse updateReserva(Long id, ReservaPutRequest reservaPutRequest) {
        Reservas existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException(id));

        existingReserva = ReservaMapper.merge(existingReserva, reservaPutRequest);

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

}
