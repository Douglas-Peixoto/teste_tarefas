package br.com.tarefas.teste_tarefas.services;

import br.com.tarefas.teste_tarefas.DTO.PessoaMediaHorasDTO;
import br.com.tarefas.teste_tarefas.DTO.PessoaTotalHorasDTO;
import br.com.tarefas.teste_tarefas.entities.Pessoa;
import br.com.tarefas.teste_tarefas.entities.Tarefa;
import br.com.tarefas.teste_tarefas.repositories.PessoaRepository;
import br.com.tarefas.teste_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Pessoa adicionarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa alterarPessoa(Pessoa pessoa, Long id){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoaExiste = pessoaOptional.get();
            pessoaExiste.setNome(pessoa.getNome());
            pessoaExiste.setDepartamento(pessoa.getDepartamento());
            pessoaExiste.setTarefasList(pessoa.getTarefasList());

            return pessoaRepository.save(pessoaExiste);
        } else {
            return  null;
        }
    }

    public boolean excluirPessoa(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            pessoaRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }

    public List<PessoaTotalHorasDTO> listarPessoasComTotalHorasGastas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaTotalHorasDTO> pessoasComTotalHoras = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            int totalHoras = calcularTotalHorasGastas(pessoa);
            PessoaTotalHorasDTO pessoaDTO = new PessoaTotalHorasDTO(pessoa.getNome(), pessoa.getDepartamento(), totalHoras);
            pessoasComTotalHoras.add(pessoaDTO);
        }

        return pessoasComTotalHoras;
    }

    private int calcularTotalHorasGastas(Pessoa pessoa) {
        int totalHoras = 0;
        for (Tarefa tarefa : pessoa.getTarefasList()) {
            totalHoras += tarefa.getDuracao();
        }
        return totalHoras;
    }

    public PessoaMediaHorasDTO buscarPessoaPorNomeEPeriodo(String nome, LocalDate dataInicio, LocalDate dataFim) {
        Pessoa pessoa = pessoaRepository.findByNome(nome);
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não encontrada com o nome: " + nome);
        }

        List<Tarefa> tarefas = tarefaRepository.findByPessoaAndPeriodo(Math.toIntExact(pessoa.getId()), dataInicio, dataFim);
        double mediaHoras = tarefas.stream().mapToInt(Tarefa::getDuracao).average().orElse(0.0);

        return new PessoaMediaHorasDTO(pessoa.getNome(), pessoa.getDepartamento(), mediaHoras);
    }
}
