package br.com.senai.api.assembler;

import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class RoleUsuarioAssembler {

    private ModelMapper modelMapper;

   /* public PessoaDTO toModel(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa toEntity(PessoaInputDTO pessoaInputDTO){
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }*/

    public RoleUsuarioDTO toModel(RoleUsuario roleUsuario){
        return modelMapper.map(roleUsuario,RoleUsuarioDTO.class);
    }

    public RoleUsuario toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO,RoleUsuario.class);
    }


}
