package br.com.senai.api.controller;

import br.com.senai.api.assembler.RoleUsuarioAssembler;
import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.RoleUsuarioRepository;
import br.com.senai.domain.service.RoleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class RoleUsuarioController {

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioService roleUsuarioService;
    private RoleUsuarioAssembler roleUsuarioAssembler;

    @PostMapping("/role")
    public RoleUsuarioDTO cadastrarRoleUsuario(@Valid @RequestBody RoleUsuarioInputDTO roleUsuarioDTO){
        RoleUsuario roleUsuarioNovo = roleUsuarioAssembler.toEntity(roleUsuarioDTO);
        RoleUsuario roleUsuario = roleUsuarioService.cadastrar(roleUsuarioNovo);
        return roleUsuarioAssembler.toModel(roleUsuario);
    }

}
