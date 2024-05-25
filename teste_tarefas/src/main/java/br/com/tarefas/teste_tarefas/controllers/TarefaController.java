package br.com.tarefas.teste_tarefas.controllers;

import br.com.tarefas.teste_tarefas.DTO.DepartamentoDTO;
import br.com.tarefas.teste_tarefas.entities.Pessoa;
import br.com.tarefas.teste_tarefas.entities.Tarefa;
import br.com.tarefas.teste_tarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    // Método para adicionar tarefa
    @PostMapping("/tarefas")
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa){
        Tarefa tarefaAdicionada = tarefaService.adicionarTarefa(tarefa);
        return new ResponseEntity<>(tarefaAdicionada, HttpStatus.CREATED);
    }

    // MÉTODO PARA ALOCAR UMA PESSOA
    @PutMapping("/tarefas/alocar/{id}")
    public ResponseEntity<Tarefa> alocarPessoa(@RequestParam Long pessoaId, @RequestParam Long tarefaId){
        boolean sucesso = tarefaService.alocarPessoa(pessoaId, tarefaId);

        if (sucesso){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //MÉTODO PARA FINALIZAR UMA TAREFA CASO SEU ESTÁGIO SEJA "FINALIZADO"
    @PutMapping("/tarefas/finalizar/{id}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long id){
        boolean sucesso = tarefaService.finalizarTarefa(id);

        if (sucesso) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tarefas/pendentes")
    public List<Tarefa> listarTarefasSemPessoaAlocadaComPrazosMaisAntigos() {
        return tarefaService.listarTarefasSemPessoaAlocadaComPrazosMaisAntigos();
    }

    @GetMapping("/departamentos")
    public List<DepartamentoDTO> listarDepartamentosComQuantidadeTarefas() {
        return tarefaService.listarDepartamentosComQuantidadeTarefas();
    }

}
