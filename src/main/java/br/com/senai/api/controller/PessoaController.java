package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.ClienteInput;
import br.com.senai.api.model.input.PessoaInput;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private PessoaRepository pessoaRepository;

    @PostMapping
    public PessoaModel cadastrar(@Valid @RequestBody ClienteInput clienteInput){
        Pessoa novaPessoa = pessoaAssembler.toEntity(clienteInput);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping
    public List<PessoaModel> listar (){return pessoaService.listar();}

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> buscar (@PathVariable Long pessoaId){
        return pessoaService.buscarPessoa(pessoaId);
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaModel> buscarPorNome (@PathVariable String pessoaNome){
        return pessoaService.buscarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{pessoaNome}")
    public List<PessoaModel> buscarContaining (@PathVariable String pessoaNome){
        return pessoaService.buscarContaining(pessoaNome);
    }

    @PutMapping ("/editar/{pessoaId}")
    public ResponseEntity<PessoaModel> editar (@Valid @PathVariable Long pessoaId,
                                               @RequestBody ClienteInput clienteInput){
        Pessoa pessoa = pessoaAssembler.toEntity(clienteInput);
        return pessoaService.editar(pessoaId, pessoa);
    }

    @DeleteMapping("/remover/{pessoaId}")
    public ResponseEntity<PessoaModel> remover (@PathVariable Long pessoaId){
        return pessoaService.remover(pessoaId);
    }


}
