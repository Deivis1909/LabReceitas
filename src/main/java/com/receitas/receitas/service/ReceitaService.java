package com.receitas.receitas.service;


import com.receitas.receitas.model.Receita;
import com.receitas.receitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired

    private ReceitaRepository receitaRepository;

    @Transactional
    public Receita salvar(Receita receita){


        Receita receit = receitaRepository.save(receita);
                return receit;


    }



    public void deletar(Long id){

        receitaRepository.deleteById(id);

    }

    public List<Receita> ListaReceitas(){

        List<Receita> receitas = receitaRepository.findAll();
        return receitas;



    }






}




