package com.receitas.receitas.controller;



import com.receitas.receitas.model.Categoria;
import com.receitas.receitas.model.Receita;
import com.receitas.receitas.repository.CategoriaRepository;
import com.receitas.receitas.repository.ReceitaRepository;
import com.receitas.receitas.service.ReceitaService;
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

    @PostMapping
    public ResponseEntity<Receita> salvar(@RequestBody Receita receita){

        Receita recei = receitaService.salvar(receita);
        return new ResponseEntity<Receita>(recei, HttpStatus.CREATED);

    }


  @PutMapping
  public ResponseEntity<Void> update(@RequestBody Receita body){

      this.receitaService.update(body);
      return ResponseEntity.ok().build();


  }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam long id){
        receitaService.deletar(id);
        return new ResponseEntity<String>("a receita numero : "+id+"  foi deletado com sucesso",HttpStatus.OK);
    }

   // @GetMapping("/lista")
    //public ResponseEntity<List<Receita>> listaTodos(){
      //  List<Receita> receitas = receitaService.ListaReceitas();
        //return new ResponseEntity<List<Receita>>(receitas,HttpStatus.FOUND);

    //}

    @GetMapping("/lista")
    public ResponseEntity<List<Receita>> listarTodas(){
        List<Receita> receitas= receitaService.ListaReceitas();

        if(!receitas.isEmpty())
            return new ResponseEntity<List<Receita>>(receitas, HttpStatus.FOUND);
        else
            return new ResponseEntity<List<Receita>>(receitas, HttpStatus.NOT_FOUND);
    }


        @GetMapping("/por-categoria/{categoriaId}")
        public List<Receita> listarReceitasPorCategoria(@PathVariable Long categoriaId) {
            Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
            if (categoria != null) {
                return receitaRepository.findByCategoria(categoria);
            }
            return Collections.emptyList();
        }
    }









