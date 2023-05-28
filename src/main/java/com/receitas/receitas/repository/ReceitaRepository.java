package com.receitas.receitas.repository;

import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    boolean existsByNome(String nome);

    boolean existsByCategoria(Categoria categoria);


}
