package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplarRepository;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExemplarService {

    @Autowired
    ExemplarRepository exemplarRepository;

    @Autowired
    LivroRepository repository;

    public List<Exemplar> listar() {
        return exemplarRepository.findAll();
    }

    public Exemplar adicionarExemplar(long id, Exemplar exemplar) {

        Livro livro = repository.findById(id).orElse(null);
        if (livro == null) {
            return null;
        } else if (exemplar.getQuantidade() <= 0) {
            return null;
        }
        Exemplar exemplarExistente = exemplarRepository.findByLivro(livro);
        if (exemplarExistente != null) {
            exemplarExistente.setQuantidade(exemplar.getQuantidade());
            return exemplarRepository.save(exemplarExistente);
        } else {
            Exemplar novoExemplar = new Exemplar();
            novoExemplar.setLivro(livro);
            novoExemplar.setQuantidade(exemplar.getQuantidade());
            return exemplarRepository.save(novoExemplar);
        }
    }

    public Exemplar excluirExemplar(long id) {
        Exemplar exemplar = exemplarRepository.findById(id).orElse(null);
        if (exemplar != null && exemplar.getQuantidade() > 0) {
            exemplar.setQuantidade(exemplar.getQuantidade() - 1);
            return exemplarRepository.save(exemplar);
        }
        return null;
    }
}
