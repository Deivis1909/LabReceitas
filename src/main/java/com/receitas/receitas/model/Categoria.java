package com.receitas.receitas.model;


import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor

@Setter

@Getter

@Table

@Entity
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String nome;


    private String descricao;






}
