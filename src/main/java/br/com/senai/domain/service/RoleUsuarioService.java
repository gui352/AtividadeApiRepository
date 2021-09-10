package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.assembler.RoleUsuarioAssembler;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.repository.RoleUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RoleUsuarioService {

    private RoleUsuarioRepository roleUsuarioRepository;

    @Transactional
    public RoleUsuario cadastrar(RoleUsuario roleUsuario){
        return roleUsuarioRepository.save(roleUsuario);
    }
}
