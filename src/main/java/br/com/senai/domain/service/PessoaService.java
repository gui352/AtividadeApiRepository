package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.RoleUsuario;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;
    private RoleUsuarioService roleUsuarioService;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
//        boolean emailValidation = pessoaRepository.findByEmail(
//                pessoa.getEmail()).isPresent();
//        if (emailValidation){
//            throw new NegocioException(
//                    "Ja existe uma  pessoa com esse e-mail cadastrado.");
//        }
        Pessoa novaPessoa = pessoaRepository.save(pessoa);
        RoleUsuario novaRole = new RoleUsuario();
        novaRole.setUsuarios_id(novaPessoa.getUsuario().getId());
        novaRole.setRole_nome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);
        return novaPessoa;
    }

    @Transactional
    public void deletar(Long pessoaId){
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<PessoaDTO> buscarId(Long pessoaId){
        return  pessoaRepository.findById(pessoaId)
                .map(pessoa ->
                        ResponseEntity.ok(pessoaAssembler.toModel(pessoa))
                )
                .orElseThrow(()->new NegocioException("Pessoa não encontrada."));
    }

    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public List<PessoaDTO> buscarNome(String nome){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(nome));
    }

    public List<PessoaDTO> listarContaining(String nomeContaining) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(nomeContaining));
    }

    public ResponseEntity<PessoaDTO> editar(Long pessoaId, Pessoa pessoa) {
        if(!pessoaRepository.existsById(pessoaId)){
            throw new NegocioException("Pessoa inexistente");
        }
        Pessoa pessoa1 = this.buscar(pessoaId);
//        if (!pessoa.getEmail().equals(pessoa1.getEmail())){
//            boolean emailValidation = pessoaRepository.findByEmail(pessoa.getEmail())
//                    .isPresent();
//            if(emailValidation){
//                throw new NegocioException("E-mail já está sendo utilizado");
//            }
//        }
        pessoa.setId(pessoaId);
        pessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }
}
