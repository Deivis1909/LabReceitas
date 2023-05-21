package com.receitas.receitas.repository;

import com.receitas.receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long> {

}
