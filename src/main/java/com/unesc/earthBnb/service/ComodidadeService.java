package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.ComodidadeRequest;
import com.unesc.earthBnb.dto.response.ComodidadeResponse;
import com.unesc.earthBnb.exception.ComodidadeNaoEncontradoException;
import com.unesc.earthBnb.mapper.ComodidadeMapper;
import com.unesc.earthBnb.model.Comodidades;
import com.unesc.earthBnb.repository.ComodidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComodidadeService {

    private final ComodidadeRepository comodidadeRepository;

    public ComodidadeService(ComodidadeRepository comodidadeRepository) {
        this.comodidadeRepository = comodidadeRepository;
    }

    @Transactional(readOnly = true)
    public ComodidadeResponse getComodidadeById(long id) {
        Comodidades comodidades = comodidadeRepository.findById(id).orElseThrow(() -> new ComodidadeNaoEncontradoException(id));
        return ComodidadeMapper.toResponse(comodidades);
    }

    @Transactional(readOnly = true)
    public List<ComodidadeResponse> getAllComodidades() {
        return comodidadeRepository.findAll().stream()
                .map(ComodidadeMapper::toResponse)
                .toList();
    }

    @Transactional
    public ComodidadeResponse createComodidade(ComodidadeRequest comodidadeRequest) {
        Comodidades comodidade = ComodidadeMapper.toEntity(comodidadeRequest);
        comodidade = comodidadeRepository.save(comodidade);
        return  ComodidadeMapper.toResponse(comodidade);
    }

    @Transactional
    public ComodidadeResponse updateComodidade(Long id, ComodidadeRequest comodidadeUpdate) {
        Comodidades comodidade = comodidadeRepository.findById(id).orElseThrow(() -> new ComodidadeNaoEncontradoException(id));

        ComodidadeMapper.merge(comodidade, comodidadeUpdate);
        comodidade = comodidadeRepository.save(comodidade);
        return ComodidadeMapper.toResponse(comodidade);
    }

    @Transactional
    public void deleteComodidade(Long id) {
        comodidadeRepository.deleteById(id);
    }

}
