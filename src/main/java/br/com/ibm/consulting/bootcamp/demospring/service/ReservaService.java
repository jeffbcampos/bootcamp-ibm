package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplarRepository;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;
import br.com.ibm.consulting.bootcamp.demospring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    ExemplarRepository exemplarRepository;

    @Autowired
    LivroRepository livroRepository;

    public Reserva criarReserva(long id, Reserva reserva) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            return null;
        }
        Exemplar exemplar = exemplarRepository.findByLivro(livro);
        if (exemplar == null || exemplar.getQuantidade() <= 0) {
            return null;
        }
        exemplar.setQuantidade(exemplar.getQuantidade() - 1);
        exemplarRepository.save(exemplar);
        reserva.setLivro(livro);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}
