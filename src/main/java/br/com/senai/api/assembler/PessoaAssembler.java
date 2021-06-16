package br.com.senai.api.assembler;

import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.ClienteInput;
import br.com.senai.domain.model.Pessoa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PessoaAssembler {

    private ModelMapper modelMapper;

    public PessoaModel toModel(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaModel.class);
    }

    public List<PessoaModel> toCollectionModel (List<Pessoa> pessoas){
        return pessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Pessoa toEntity(@Valid ClienteInput pessoaInput){
        return modelMapper.map(pessoaInput, Pessoa.class);
    }

}
