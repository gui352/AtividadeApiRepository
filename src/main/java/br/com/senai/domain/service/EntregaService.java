package br.com.senai.domain.service;

import br.com.senai.domain.exception.EntidadeNaoEncontradaException;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entregaEncontrada = buscaEntrega(entregaId);
        entregaEncontrada.finalizar();
        entregaRepository.save(entregaEncontrada);
    }

    public Entrega buscaEntrega(Long entregaId){
        return  entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }
}
