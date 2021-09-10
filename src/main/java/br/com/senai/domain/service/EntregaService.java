package br.com.senai.domain.service;

import br.com.senai.domain.exception.EntidadeNaoEncontradaException;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.repository.EntregaRopository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EntregaService {

    private EntregaRopository entregaRopository;

    @Transactional
    public void finalizar(Long entregaId){
        Entrega entrega = buscaEntrega(entregaId);
        entrega.finalizar();
        entregaRopository.save(entrega);
    }

    public Entrega buscaEntrega(Long entregaId){
        return entregaRopository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
