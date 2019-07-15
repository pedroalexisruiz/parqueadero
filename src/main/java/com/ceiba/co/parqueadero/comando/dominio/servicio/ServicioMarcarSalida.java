package com.ceiba.co.parqueadero.comando.dominio.servicio;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;

public interface ServicioMarcarSalida {

	public Ticket registrarSalidaDelVehiculo(String placa);
}
