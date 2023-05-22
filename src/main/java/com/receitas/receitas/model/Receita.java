package com.receitas.receitas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String modo_de_Preparo;


    @Column(nullable = false)
    private Boolean restricoes;


    @ManyToOne
    @JoinColumn(name="categoria_id",foreignKey = @ForeignKey(name="fk_categoria"),unique = true)
    private Categoria categoria;










}
