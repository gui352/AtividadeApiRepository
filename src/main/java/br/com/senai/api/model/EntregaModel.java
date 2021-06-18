package br.com.senai.api.model;

import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import javax.transaction.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EntregaModel {

    private Pessoa pessoa;

    private Long id;

    private DestinatarioModel destinatario;

    private BigDecimal taxa;

    private LocalDateTime dataPedido;

    private LocalDateTime dataFinalizacao;

    private StatusEntrega status;

}
