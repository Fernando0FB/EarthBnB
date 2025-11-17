package com.unesc.earthBnb.service;

import com.unesc.earthBnb.dto.request.AcomodacaoPostRequest;
import com.unesc.earthBnb.dto.request.AcomodacaoPutRequest;
import com.unesc.earthBnb.dto.response.AcomodacaoResponse;
import com.unesc.earthBnb.exception.AcomodacaoNaoEncontradaException;
import com.unesc.earthBnb.exception.ComodidadeNaoEncontradoException;
import com.unesc.earthBnb.mapper.AcomodacaoMapper;
import com.unesc.earthBnb.mapper.ComodidadeMapper;
import com.unesc.earthBnb.model.Acomodacoes;
import com.unesc.earthBnb.repository.AcomodacaoRepository;
import com.unesc.earthBnb.repository.ComodidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcomodacaoService {

    private final AcomodacaoRepository acomodacaoRepository;
    private final ComodidadeRepository comodidadeRepository;

    public AcomodacaoService(AcomodacaoRepository acomodacaoRepository, ComodidadeService comodidadeService, ComodidadeRepository comodidadeRepository) {
        this.acomodacaoRepository = acomodacaoRepository;
        this.comodidadeRepository = comodidadeRepository;
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
    public AcomodacaoResponse createAcomodacao(AcomodacaoPostRequest acomodacaoPostRequest) {
        Acomodacoes acomodacao = AcomodacaoMapper.toEntity(acomodacaoPostRequest);
        acomodacao = acomodacaoRepository.save(acomodacao);
        return AcomodacaoMapper.toResponse(acomodacao);
    }

    @Transactional
    public AcomodacaoResponse updateAcomodacao(Long id, AcomodacaoPutRequest acomodacaoPutRequest) {
        Acomodacoes existingAcomodacao = acomodacaoRepository.findById(id)
                .orElseThrow(() -> new AcomodacaoNaoEncontradaException(id));

        AcomodacaoMapper.merge(existingAcomodacao, acomodacaoPutRequest);
        if(acomodacaoPutRequest.comodidades() != null) {
            existingAcomodacao.setComodidades(
                    acomodacaoPutRequest.comodidades().stream()
                            .map(comodidadeReq -> comodidadeRepository.findById(comodidadeReq.id()).orElseThrow(
                                    () -> new ComodidadeNaoEncontradoException(comodidadeReq.id())
                            ))
                            .collect(Collectors.toList())
            );
        }

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
