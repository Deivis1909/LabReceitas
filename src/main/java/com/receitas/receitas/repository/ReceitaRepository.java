package com.receitas.receitas.repository;

import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long> {
    List<Receita> findByCategoria(Categoria categoria);

    public Receita findById(@Param("id") long id);


}
