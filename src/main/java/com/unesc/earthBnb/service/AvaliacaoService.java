package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.AvaliacaoPostRequest;
import com.unesc.earthBnb.dto.request.AvaliacaoPutRequest;
import com.unesc.earthBnb.dto.response.AvaliacaoResponse;
import com.unesc.earthBnb.exception.AvaliacaoNaoEncontradoException;
import com.unesc.earthBnb.mapper.AvaliacaoMapper;
import com.unesc.earthBnb.model.Avaliacoes;
import com.unesc.earthBnb.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional(readOnly = true)
    public AvaliacaoResponse getAvaliacaoById(Long id) {
        Avaliacoes avaliacoes = avaliacaoRepository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontradoException(id));
        return AvaliacaoMapper.toResponse(avaliacoes);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoResponse> getAllAvaliacoes(){
        return avaliacaoRepository.findAll().stream()
                .map(AvaliacaoMapper::toResponse)
                .toList();
    }

    @Transactional
    public AvaliacaoResponse createAvaliacoes(AvaliacaoPostRequest avaliacaoPostRequest) {
        Avaliacoes avaliacoes = AvaliacaoMapper.toEntity(avaliacaoPostRequest);
        avaliacoes = avaliacaoRepository.save(avaliacoes);
        return AvaliacaoMapper.toResponse(avaliacoes);
    }

    @Transactional
    public AvaliacaoResponse updateAvaliacoes(Long id, AvaliacaoPutRequest avaliacaoPutRequest) {
        Avaliacoes avaliacoes = avaliacaoRepository.findById(id).orElseThrow(() -> new AvaliacaoNaoEncontradoException(id));

        AvaliacaoMapper.merge(avaliacoes, avaliacaoPutRequest);
        avaliacoes = avaliacaoRepository.save(avaliacoes);
        return AvaliacaoMapper.toResponse(avaliacoes);
    }

    @Transactional
    public void deleteAvaliacoes(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new AvaliacaoNaoEncontradoException(id);
        }
        avaliacaoRepository.deleteById(id);
    }

}
