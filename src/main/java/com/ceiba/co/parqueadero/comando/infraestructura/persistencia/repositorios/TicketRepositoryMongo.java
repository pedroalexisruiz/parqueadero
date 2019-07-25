package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.repositorio.RepositorioTicket;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.builder.TicketBuilderNoSql;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios.implejpa.TicketRepositoryNoSql;

public class TicketRepositoryMongo implements RepositorioTicket {

	@Autowired
	TicketBuilderNoSql ticketNoSqlBuilder;
	@Autowired
	TicketRepositoryNoSql noSqlrepository;

	@Override
	public LocalDateTime registrarEntrada(Ticket ticket) {
		return noSqlrepository.save(ticketNoSqlBuilder.convertirAEntidad(ticket)).getHoraDeEntrada();
	}

	@Override
	public void registrarSalida(Ticket ticket) {
		noSqlrepository.save(ticketNoSqlBuilder.convertirAEntidad(ticket));
	}

	@Override
	public boolean existeVehiculoEnParqueadero(String plate) {
		return noSqlrepository.existeVehiculoEnParqueadero(plate);
	}

	@Override
	public Ticket buscarPorPlacaSinSalida(String plate) {
		return ticketNoSqlBuilder.convertirADominio(noSqlrepository.buscarPorPlacaSinSalida(plate));
	}

	@Override
	public Long contarVehiculosParqueadosPorTipo(String tipoDeVehiculo) {
		return noSqlrepository.countByTipoDeVehiculoAndHoraDeSalidaIsNull(tipoDeVehiculo);
	}

	@Override
	public void borrarTodos() {
		noSqlrepository.deleteAll();
	}

}
