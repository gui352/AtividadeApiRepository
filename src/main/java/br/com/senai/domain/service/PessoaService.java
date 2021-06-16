package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
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
    private ModelMapper modelMapper;


    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {
        boolean emailValidation = pessoaRepository.findByEmail(
                pessoa.getEmail()).isPresent();
        if (emailValidation) {
            throw new NegocioException(
                    "Ja existe uma  pessoa com esse e-mail cadastrado.");
        }
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaModel> listar(){ return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());}

    public ResponseEntity<PessoaModel> buscarPessoa(Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> {
                    return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public List<PessoaModel> buscarPorNome (String nomePessoa){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(nomePessoa));
    }

    public List<PessoaModel> buscarContaining (String nomePessoa){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(nomePessoa));
    }

    public ResponseEntity<PessoaModel> editar (Long pessoaId, Pessoa pessoa){

        if(!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }

        pessoa.setId(pessoaId);
        pessoa = pessoaRepository.save(pessoa);

        PessoaModel pessoaModel = modelMapper.map(pessoa, PessoaModel.class);

        return ResponseEntity.ok(pessoaModel);
    }

    public ResponseEntity<PessoaModel> remover  (Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }

        pessoaRepository.deleteById(pessoaId);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    public void excluir(Long pessoaId) {
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada"));
    }
}
