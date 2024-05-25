package br.com.tarefas.teste_tarefas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaTotalHorasDTO {
    private String nome;
    private String departamento;
    private int totalHorasGastas;
}
