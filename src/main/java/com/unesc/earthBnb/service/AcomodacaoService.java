package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.AcomodacaoRequest;
import com.unesc.earthBnb.dto.response.AcomodacaoResponse;
import com.unesc.earthBnb.exception.AcomodacaoNaoEncontradaException;
import com.unesc.earthBnb.mapper.AcomodacaoMapper;
import com.unesc.earthBnb.model.Acomodacoes;
import com.unesc.earthBnb.repository.AcomodacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcomodacaoService {

    private final AcomodacaoRepository acomodacaoRepository;

    public AcomodacaoService(AcomodacaoRepository acomodacaoRepository) {
        this.acomodacaoRepository = acomodacaoRepository;
    }

    @Transactional(readOnly = true)
    public AcomodacaoResponse getAcomodacaoById(Long id) {
        Acomodacoes acomodacao = acomodacaoRepository.findById(id)
                .orElseThrow(() -> new AcomodacaoNaoEncontradaException(id));

        return AcomodacaoMapper.toResponse(acomodacao);
    }

    @Transactional(readOnly = true)
    public List<AcomodacaoResponse> getAllAcomodacoes() {
        List<Acomodacoes> acomodacoes = acomodacaoRepository.findAll();
        return acomodacoes.stream()
                .map(AcomodacaoMapper::toResponse)
                .toList();
    }

    @Transactional
    public AcomodacaoResponse createAcomodacao(AcomodacaoRequest acomodacaoRequest) {
        Acomodacoes acomodacao = AcomodacaoMapper.toEntity(acomodacaoRequest);
        acomodacao = acomodacaoRepository.save(acomodacao);
        return AcomodacaoMapper.toResponse(acomodacao);
    }

    @Transactional
    public AcomodacaoResponse updateAcomodacao(Long id, AcomodacaoRequest acomodacaoRequest) {
        Acomodacoes existingAcomodacao = acomodacaoRepository.findById(id)
                .orElseThrow(() -> new AcomodacaoNaoEncontradaException(id));

        AcomodacaoMapper.merge(existingAcomodacao, acomodacaoRequest);

        existingAcomodacao = acomodacaoRepository.save(existingAcomodacao);

        return AcomodacaoMapper.toResponse(existingAcomodacao);
    }

    @Transactional
    public void deleteAcomodacao(Long id) {
        if (!acomodacaoRepository.existsById(id)) {
            throw new AcomodacaoNaoEncontradaException(id);
        }
        acomodacaoRepository.deleteById(id);
    }

}
