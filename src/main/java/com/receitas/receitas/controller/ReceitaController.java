package com.receitas.receitas.controller;



import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import com.receitas.receitas.repository.CategoriaRepository;
import com.receitas.receitas.repository.ReceitaRepository;
import com.receitas.receitas.service.CategoriaService;
import com.receitas.receitas.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired //injecao de depencias/ como estanciar a classe
    private ReceitaService receitaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Receita> criarReceita(@RequestBody Receita receita) {
        Receita novaReceita = receitaService.criarReceita(receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }


    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        List<Receita> receitas = receitaService.listarReceitas();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> obterReceitaPorId(@PathVariable Long id) {
        Receita receita = receitaService.obterReceitaPorId(id);
        return ResponseEntity.ok(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(@PathVariable Long id, @RequestBody Receita receitaAtualizada) {
        receitaAtualizada = receitaService.atualizarReceita(id, receitaAtualizada);
        return ResponseEntity.ok(receitaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReceita(@PathVariable Long id) {
        receitaService.excluirReceita(id);
        return ResponseEntity.noContent().build();
    }
}










