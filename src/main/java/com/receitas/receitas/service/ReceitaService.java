package com.receitas.receitas.service;


import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import com.receitas.receitas.repository.CategoriaRepository;
import com.receitas.receitas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    ////////******* TESTTT
    public Receita criarReceita(Receita receita) {
        if (receita.getNome() == null || receita.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da receita é obrigatório");
        }

        if (receitaRepository.existsByNome(receita.getNome())) {
            throw new IllegalArgumentException("Já existe uma receita com o mesmo nome");
        }

        Categoria categoria = receita.getCategoria();
        if (categoria != null && categoria.getId() != null) {
            if (!categoriaRepository.existsById(categoria.getId())) {
                throw new IllegalArgumentException("A categoria associada à receita não existe");
            }
        }

        return receitaRepository.save(receita);
    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public Receita obterReceitaPorId(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Receita não encontrada"));
    }

    public Receita atualizarReceita(Long id, Receita receitaAtualizada) {
        Receita receita = obterReceitaPorId(id);

        if (receitaAtualizada.getNome() != null && !receitaAtualizada.getNome().isEmpty()) {
            receita.setNome(receitaAtualizada.getNome());
        }

        if (receitaAtualizada.getIngredientes() != null && !receitaAtualizada.getIngredientes().isEmpty()) {
            receita.setIngredientes(receitaAtualizada.getIngredientes());
        }

        if (receitaAtualizada.getModoPreparo() != null && !receitaAtualizada.getModoPreparo().isEmpty()) {
            receita.setModoPreparo(receitaAtualizada.getModoPreparo());
        }

        receita.setPossuiRestricoes(receitaAtualizada.isPossuiRestricoes());

        Categoria categoriaAtualizada = receitaAtualizada.getCategoria();
        if (categoriaAtualizada != null && categoriaAtualizada.getId() != null) {
            Categoria categoria = categoriaRepository.findById(categoriaAtualizada.getId())
                    .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));
            receita.setCategoria(categoria);
        } else {
            receita.setCategoria(null);
        }

        return receitaRepository.save(receita);
    }

    public void excluirReceita(Long id) {
        Receita receita = obterReceitaPorId(id);
        receitaRepository.delete(receita);
    }
}

