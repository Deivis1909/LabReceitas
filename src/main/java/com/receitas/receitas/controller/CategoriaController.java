package com.receitas.receitas.controller;


import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.repository.CategoriaRepository;
import com.receitas.receitas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;



    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){

        Categoria cat = categoriaService.salvar(categoria);
        return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);


    }
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listaCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obterCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
         categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaAtualizada);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}










