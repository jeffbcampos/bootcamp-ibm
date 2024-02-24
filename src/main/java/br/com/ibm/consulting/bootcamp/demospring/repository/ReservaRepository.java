package br.com.ibm.consulting.bootcamp.demospring.repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    List<Reserva> findByLivro(Livro livro);
}
