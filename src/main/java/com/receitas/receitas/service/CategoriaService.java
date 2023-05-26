package com.receitas.receitas.service;

import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import com.receitas.receitas.repository.CategoriaRepository;

import com.receitas.receitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Transactional
    public Categoria salvar(Categoria categoria) {

        // verifica se ja tem uma categoria com esse nome
        if (categoriaRepository.equals(categoria.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria de restrição com esse nome.");
        }

        Categoria cat = categoriaRepository.save(categoria);
        return cat;

    }
    @Transactional
    public void deletarCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
        if (categoria != null) {
            List<Receita> receitas = receitaRepository.findByCategoria(categoria);
            if (receitas.isEmpty()) {
                categoriaRepository.delete(categoria);
            } else {
                throw new RuntimeException("Não é possível excluir a categoria, pois existem receitas associadas a ela.");
            }
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }

    public List<Categoria> listaCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }


}