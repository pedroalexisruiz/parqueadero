package com.ceiba.co.parqueadero.comando.dominio.servicio;

import java.time.LocalDateTime;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;

public interface ServicioRegistrarEntrada {

	public LocalDateTime registrarEntradaDeVehiculo(Ticket ticket);
}
