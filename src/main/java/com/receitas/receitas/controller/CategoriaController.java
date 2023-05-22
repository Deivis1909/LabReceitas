package com.receitas.receitas.controller;


import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.repository.CategoriaRepository;
import com.receitas.receitas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping
    public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria){
        Categoria cate = categoriaService.salvar(categoria);
        return new ResponseEntity<Categoria>(cate,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam long id){

        categoriaService.deletarCategoria(id);

        return new ResponseEntity<String>("A categoria numero"+id+" foi deletada com sucesso",HttpStatus.OK);


    }
    @GetMapping
    public ResponseEntity<List<Categoria>> listaTodos(){
        List<Categoria> categorias = categoriaService.listaCategorias();
        return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.FOUND);


    }










}
