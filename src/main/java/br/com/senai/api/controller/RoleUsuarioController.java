package br.com.senai.api.controller;

import br.com.senai.api.assembler.RoleUsuarioAssembler;
import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.RoleUsuarioRepository;
import br.com.senai.domain.service.RoleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleUsuarioController {

    RoleUsuarioAssembler roleUsuarioAssembler;
    RoleUsuarioRepository roleUsuarioRepository;
    RoleUsuarioService roleUsuarioService;

    @PostMapping
    public RoleUsuarioDTO cadastrar(@Valid @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RoleUsuario novaRole = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        RoleUsuario roleUsuario = roleUsuarioService.cadastrar(novaRole);
        return roleUsuarioAssembler.toModel(roleUsuario);
    }

    @GetMapping
    public List<RoleUsuarioDTO> listar (){return roleUsuarioService.listar();}

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> buscarPorId(@PathVariable Long roleId){
        return roleUsuarioService.buscarId(roleId);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> editar(@Valid @PathVariable Long roleId, @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RoleUsuario roleUsuario1 = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        roleUsuarioService.editar(roleId, roleUsuario1);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuario1));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> deletar(@PathVariable Long roleId){
        if (!roleUsuarioRepository.existsById(roleId)){
            return ResponseEntity.notFound().build();
        }
        roleUsuarioService.deletar(roleId);
        return ResponseEntity.noContent().build();
    }
}
