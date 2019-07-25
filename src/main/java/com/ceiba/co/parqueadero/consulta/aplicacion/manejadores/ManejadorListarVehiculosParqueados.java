package com.ceiba.co.parqueadero.consulta.aplicacion.manejadores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ceiba.co.parqueadero.consulta.dominio.dao.DaoTicket;
import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;

@Component
public class ManejadorListarVehiculosParqueados {

	private final DaoTicket daoTicket;

	@Autowired
	public ManejadorListarVehiculosParqueados(@Qualifier("daoTicketMongo") DaoTicket daoTicket) {
		this.daoTicket = daoTicket;
	}

	public List<Ticket> ejecutar() {
		return daoTicket.listarVehiculosIngresados();
	}
}
