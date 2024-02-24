package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplarRepository;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ExemplarService {

    @Autowired
    ExemplarRepository exemplarRepository;

    @Autowired
    LivroRepository repository;

    public Exemplar adicionarExemplar(long id, Exemplar exemplar) {

        Livro livro = repository.findById(id).orElse(null);
        if (livro == null) {
            return null;
        }
        Exemplar exemplarExistente = exemplarRepository.findByLivro(livro);
        if (exemplarExistente != null) {
            exemplarExistente.setQuantidade(exemplarExistente.getQuantidade() + 1);
            return exemplarRepository.save(exemplarExistente);
        } else {
            Exemplar novoExemplar = new Exemplar();
            novoExemplar.setLivro(livro);
            novoExemplar.setQuantidade(1);
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