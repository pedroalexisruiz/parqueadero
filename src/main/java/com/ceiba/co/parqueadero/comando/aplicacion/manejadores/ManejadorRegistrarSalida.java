package com.ceiba.co.parqueadero.comando.aplicacion.manejadores;

import org.springframework.stereotype.Component;

import com.ceiba.co.parqueadero.comando.aplicacion.entidad.InformacionDeSalida;
import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.servicio.ServicioMarcarSalida;

@Component
public class ManejadorRegistrarSalida
		implements ManejadorRespuestaComando<String, RespuestaComando<InformacionDeSalida>> {

	private final ServicioMarcarSalida servicioDeRegistroDeSalida;

	public ManejadorRegistrarSalida(ServicioMarcarSalida servicioDeRegistroDeSalida) {
		this.servicioDeRegistroDeSalida = servicioDeRegistroDeSalida;
	}

	@Override
	public RespuestaComando<InformacionDeSalida> ejecutar(String placa) {
		Ticket ticket = this.servicioDeRegistroDeSalida.registrarSalidaDelVehiculo(placa);
		return new RespuestaComando<>(new InformacionDeSalida(ticket.getHoraDeEntrada(), ticket.getTotalAPagar()));
	}

}
