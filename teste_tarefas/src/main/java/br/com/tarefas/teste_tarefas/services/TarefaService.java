package br.com.tarefas.teste_tarefas.services;

import br.com.tarefas.teste_tarefas.DTO.DepartamentoDTO;
import br.com.tarefas.teste_tarefas.entities.Pessoa;
import br.com.tarefas.teste_tarefas.entities.Tarefa;
import br.com.tarefas.teste_tarefas.repositories.PessoaRepository;
import br.com.tarefas.teste_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tarefa adicionarTarefa(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public boolean alocarPessoa(Long pessoaId, Long tarefaId){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(tarefaId);

        if (pessoaOptional.isPresent() && tarefaOptional.isPresent()){
            Pessoa pessoa = pessoaOptional.get();
            Tarefa tarefa = tarefaOptional.get();

            if (pessoa.getDepartamento().equals(tarefa.getDepartamento())){
                tarefa.setPessoa(pessoa);
                tarefaRepository.save(tarefa);
                return true;
            }
        }
        return false;
    }

    public boolean finalizarTarefa(Long tarefaId){
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(tarefaId);

        if (tarefaOptional.isPresent()){
            Tarefa tarefa = tarefaOptional.get();
            tarefa.setStatusFinalizado("finalizado");
            tarefaRepository.save(tarefa);
            return true;
        }
        return false;
    }

    public List<Tarefa> listarTarefasSemPessoaAlocadaComPrazosMaisAntigos() {
        return tarefaRepository.findTarefasSemPessoaAlocadaOrderByPrazo().subList(0, 3);
    }

    public List<DepartamentoDTO> listarDepartamentosComQuantidadeTarefas() {
        List<Object[]> tarefasPorDepartamento = tarefaRepository.contarTarefasPorDepartamento();
        List<DepartamentoDTO> departamentosDTO = new ArrayList<>();

        for (Object[] tarefa : tarefasPorDepartamento) {
            String departamento = (String) tarefa[0];
            Long quantidadeTarefas = (Long) tarefa[1];

            DepartamentoDTO departamentoDTO = new DepartamentoDTO(departamento, null, quantidadeTarefas);
            departamentosDTO.add(departamentoDTO);
        }

        return departamentosDTO;
    }

}
