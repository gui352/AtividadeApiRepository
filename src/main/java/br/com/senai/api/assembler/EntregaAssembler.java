package br.com.senai.api.assembler;

import br.com.senai.api.model.EntregaDTO;
import br.com.senai.api.model.input.EntregaInputDTO;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.repository.EntregaRopository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;
    private EntregaRopository entregaRopository;


    public EntregaDTO toModel(Entrega entrega){
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toColletionModel(List<Entrega> entregas){
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInputDTO entregaInputDTO){
        return modelMapper.map(entregaInputDTO, Entrega.class);
    }
}
