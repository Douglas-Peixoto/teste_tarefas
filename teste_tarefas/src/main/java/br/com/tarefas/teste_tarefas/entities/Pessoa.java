package br.com.tarefas.teste_tarefas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String departamento;
    @OneToMany(mappedBy = "pessoa")
    private List<Tarefa> tarefasList = new ArrayList<>();
}
