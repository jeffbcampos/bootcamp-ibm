package br.com.ibm.consulting.bootcamp.demospring.controller;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplarRepository;
import br.com.ibm.consulting.bootcamp.demospring.service.ExemplarService;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
public class ExemplarController {

    @Autowired
    ExemplarService exemplarService;

    @Autowired
    ExemplarRepository exemplarRepository;

    @Autowired
    LivroService service;

    @GetMapping("/exemplares")
    public List<Exemplar> listar() {
        return exemplarService.listar();
    }

    @PostMapping("/{id}/exemplares")
    public ResponseEntity<Exemplar> adicionarExemplar(@PathVariable long id, @RequestBody Exemplar exemplar) {
        Exemplar savedExemplar = exemplarService.adicionarExemplar(id, exemplar);
        if (savedExemplar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedExemplar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/exemplares")
    public ResponseEntity<Void> excluirExemplar(@PathVariable long id) {
        Livro livro = service.obter(id);
        if (livro != null) {
            Exemplar exemplar = exemplarRepository.findByLivro(livro);
            if (exemplar != null && exemplar.getQuantidade() > 0) {
                exemplar.setQuantidade(exemplar.getQuantidade() - 1);
                exemplarRepository.save(exemplar);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
