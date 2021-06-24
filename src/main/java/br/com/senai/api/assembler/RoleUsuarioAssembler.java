package br.com.senai.api.assembler;

import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.RoleUsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RoleUsuarioAssembler {

    private ModelMapper modelMapper;
    private RoleUsuarioRepository roleUsuarioRepository;

    public RoleUsuario toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO, RoleUsuario.class);
    }

    public List<RoleUsuarioDTO> toCollectionModel(List<RoleUsuario> roleUsuarios) {
        return roleUsuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public RoleUsuarioDTO toModel(RoleUsuario roleUsuario){
        return modelMapper.map(roleUsuario, RoleUsuarioDTO.class);
    }

}
