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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Transactional
    public Categoria salvar(Categoria categoria) {

        Categoria cat = categoriaRepository.save(categoria);
        return cat;

    }

    public List<Categoria> listaCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criarCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório");
        }

        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com o mesmo nome");
        }

        return categoriaRepository.save(categoria);
    }



    public Categoria obterCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));
    }

    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));

        // Verifica se o nome da categoria já está em uso por outra categoria
        if (!categoriaAtualizada.getNome().equals(categoriaExistente.getNome())
                && categoriaRepository.existsByNome(categoriaAtualizada.getNome())) {
            throw new IllegalArgumentException("Nome da categoria já está em uso");
        }

        categoriaExistente.setNome(categoriaAtualizada.getNome());
        categoriaExistente.setDescricao(categoriaAtualizada.getDescricao());

        return categoriaRepository.save(categoriaExistente);
    }

    public void excluirCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));

        // Verifica se existem receitas associadas a essa categoria
        if (receitaRepository.existsByCategoria(categoria)) {
            throw new IllegalArgumentException("Não é possível excluir uma categoria com receitas associadas");
        }

        categoriaRepository.delete(categoria);
    }
}
