package br.com.tarefas.teste_tarefas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {
    private String nomeDepartamento;
    private Long quantidadeTarefas;

    public DepartamentoDTO(String departamento, Object o, Long quantidadeTarefas) {
    }
}
