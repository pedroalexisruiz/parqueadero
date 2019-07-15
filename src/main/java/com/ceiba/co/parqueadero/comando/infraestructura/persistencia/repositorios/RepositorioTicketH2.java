package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.repositorio.RepositorioTicket;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.builder.TicketBuilder;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.entidad.EntidadTicket;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios.implejpa.RepositorioTicketJpa;

public class RepositorioTicketH2 implements RepositorioTicket {

	@Autowired
	RepositorioTicketJpa repositorioTicket;
	@Autowired
	TicketBuilder ticketBuilder;

	@Override
	public LocalDateTime registrarEntrada(Ticket ticket) {
		return repositorioTicket.save(ticketBuilder.convertirAEntidad(ticket)).getHoraDeEntrada();
	}

	@Override
	public void registrarSalida(Ticket ticket) {
		repositorioTicket.save(ticketBuilder.convertirAEntidad(ticket));
	}

	@Override
	public boolean existeVehiculoEnParqueadero(String placa) {
		return repositorioTicket.existeVehiculoEnParqueadero(placa) == "true";
	}

	@Override
	public Ticket buscarPorId(Long id) {
		Optional<EntidadTicket> ticket = repositorioTicket.findById(id);
		return ticketBuilder.convertirADominio(ticket.orElse(null));
	}

	@Override
	public Ticket buscarPorPlacaSinSalida(String placa) {
		return ticketBuilder.convertirADominio(repositorioTicket.buscarPorPlacaSinSalida(placa));
	}

	@Override
	public Long contarVehiculosParqueadosPorTipo(String tipoDeVehiculo) {
		return repositorioTicket.countByTipoDeVehiculoAndHoraDeSalidaIsNull(tipoDeVehiculo);
	}

	@Override
	public void borrarTodos() {
		repositorioTicket.deleteAll();
	}
}
