package com.ceiba.co.parqueadero.consulta.dominio.dao;

import java.util.List;

import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;

public interface DaoTicket {

	List<Ticket> listarVehiculosIngresados();

}
