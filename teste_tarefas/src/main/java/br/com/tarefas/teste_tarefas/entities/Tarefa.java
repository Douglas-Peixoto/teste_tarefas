package br.com.tarefas.teste_tarefas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String prazo;
    @Column(nullable = false)
    private String departamento;
    @Column(nullable = false)
    private int duracao;
    @ManyToOne
    private Pessoa pessoa;
    @Column(nullable = false)
    private String statusFinalizado;
}
