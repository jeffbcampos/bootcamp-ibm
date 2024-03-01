package br.com.ibm.consulting.bootcamp.demospring.controller;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping("/{id}/reservas")
    public ResponseEntity<Reserva> criarReserva(@PathVariable long id, @RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.criarReserva(id, reserva);
        if (novaReserva == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/reservas")
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }
}
