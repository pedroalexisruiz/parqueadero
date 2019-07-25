package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.repositorio.RepositorioTicket;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.builder.TicketBuilder;
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
