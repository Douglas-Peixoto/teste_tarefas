package br.com.tarefas.teste_tarefas.DTO;

import lombok.Data;

@Data
public class PessoaMediaHorasDTO {
    private String nome;
    private String departamento;
    private double mediaHorasGastas;

    public PessoaMediaHorasDTO(String nome, String departamento, double mediaHorasGastas) {
        this.nome = nome;
        this.departamento = departamento;
        this.mediaHorasGastas = mediaHorasGastas;
    }
}

