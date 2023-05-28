package com.receitas.receitas.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    //false nao pode ser nulo

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String ingredientes;

    @Column(nullable = false)
    private String modoPreparo;

    @Column(nullable = false)
    private boolean possuiRestricoes;

    @ManyToOne
    private Categoria categoria;



}
