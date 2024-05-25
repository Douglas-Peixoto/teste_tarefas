package br.com.tarefas.teste_tarefas.controllers;

import br.com.tarefas.teste_tarefas.DTO.PessoaMediaHorasDTO;
import br.com.tarefas.teste_tarefas.DTO.PessoaTotalHorasDTO;
import br.com.tarefas.teste_tarefas.entities.Pessoa;
import br.com.tarefas.teste_tarefas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    // MÉTODO PARA ADICIONAR PESSOA
    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa){
        Pessoa pessoaAdicionada = pessoaService.adicionarPessoa(pessoa);
        return new ResponseEntity<>(pessoaAdicionada, HttpStatus.CREATED);
    }

    // MÉTODO PARA ALTERAR E ATUALIZAR UMA PESSOA
    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        Pessoa pessoaAtualizada = pessoaService.alterarPessoa(pessoa, id);
        if (pessoaAtualizada != null){
            return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // MÉTODO PARA EXCLUIR PESSOA
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> excluirPessoa(@PathVariable Long id){
        boolean pessoaExcluida = pessoaService.excluirPessoa(id);
        if (pessoaExcluida){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //MÉTODO QUE LISTA PESSOAS COM NOME, DEPARTAMENTO E HORAS GASTAS
    @GetMapping("/pessoas")
    public List<PessoaTotalHorasDTO> listarPessoaComHorasGastas(){
        return pessoaService.listarPessoasComTotalHorasGastas();
    }

    @GetMapping("/pessoas/gastos")
    public PessoaMediaHorasDTO buscarPessoaPorNomeEPeriodo(@RequestParam String nome, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        return pessoaService.buscarPessoaPorNomeEPeriodo(nome, dataInicio, dataFim);
    }
}
