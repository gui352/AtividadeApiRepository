package br.com.senai.domain.service;

import br.com.senai.api.assembler.RoleUsuarioAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.RoleUsuarioDTO;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.RoleUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleUsuarioService {

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioAssembler roleUsuarioAssembler;

    @Transactional
    public RoleUsuario cadastrar(RoleUsuario roleUsuario){return roleUsuarioRepository.save(roleUsuario);}

    public List<RoleUsuarioDTO> listar(){
        return roleUsuarioAssembler.toCollectionModel(roleUsuarioRepository.findAll());
    }

    public RoleUsuario buscar(Long roleId){
        return roleUsuarioRepository.findById(roleId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> buscarId(Long roleId){
        return roleUsuarioRepository.findById(roleId)
                .map( roleUsuario ->
                          ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuario))
                )
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> editar(Long roleId, RoleUsuario roleUsuario){
        if (!roleUsuarioRepository.existsById(roleId)){
            throw new NegocioException("Pessoa inexistente.");
        }
        RoleUsuario roleUsuario1 = this.buscar(roleId);
        roleUsuario.setId(roleId);
        roleUsuario = roleUsuarioRepository.save(roleUsuario);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuario));
    }

    @Transactional
    public void deletar(Long roleId){ roleUsuarioRepository.deleteById(roleId); }


}
