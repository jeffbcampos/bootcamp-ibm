package br.com.ibm.consulting.bootcamp.demospring.service;

import java.util.List;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplarRepository;
import br.com.ibm.consulting.bootcamp.demospring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;

@Component
public class LivroService {
	
	@Autowired
	LivroRepository repository;

	@Autowired
	ReservaRepository reservaRepository;

	@Autowired
	ExemplarRepository exemplarRepository;
	
	public Livro criarLivro(Livro l) {
		return repository.saveAndFlush(l);
	}
	
	public List<Livro> listar() {
		return repository.findAll();
	}
	
	public Livro obter(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void alterar(long id, Livro novoLivro) {
		var alterado = new Livro(id, novoLivro.getAutor(), novoLivro.getTitulo(), novoLivro.getAnoPublicacao());
		repository.saveAndFlush(alterado);
	}
	
	public void excluir(long id) {
		Livro livro = repository.findById(id).orElse(null);
		if (livro != null) {

			List<Reserva> reservas = reservaRepository.findByLivro(livro);
			Exemplar exemplar = exemplarRepository.findByLivro(livro);

			if (reservas.isEmpty() && exemplar == null) {
				repository.delete(livro);
			} else {

				reservaRepository.deleteAll(reservas);
				if (exemplar != null) {
					exemplarRepository.delete(exemplar);
				}
				repository.delete(livro);
			}
		}
	}

}
