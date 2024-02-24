package br.com.ibm.consulting.bootcamp.demospring.repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    Exemplar findByLivro(Livro livro);

}
