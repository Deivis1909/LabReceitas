package com.receitas.receitas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
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
    private String Modo_de_Preparo;


    @Column(nullable = false)
    private Boolean restricoes;


    @ManyToOne
    @JoinColumn(name="categoria_id",foreignKey = @ForeignKey(name="fk_categoria"),unique = true)
    private Categoria categoria;










}
