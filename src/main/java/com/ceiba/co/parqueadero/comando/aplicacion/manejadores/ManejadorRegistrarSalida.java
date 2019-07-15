package com.ceiba.co.parqueadero.comando.aplicacion.manejadores;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.co.parqueadero.comando.dominio.servicio.ServicioMarcarSalida;

@Component
public class ManejadorRegistrarSalida implements ManejadorRespuestaComando<String, RespuestaComando<LocalDateTime>> {

	private final ServicioMarcarSalida servicioDeRegistroDeSalida;

	public ManejadorRegistrarSalida(ServicioMarcarSalida servicioDeRegistroDeSalida) {
		this.servicioDeRegistroDeSalida = servicioDeRegistroDeSalida;
	}

	@Override
	public RespuestaComando<LocalDateTime> ejecutar(String placa) {
		return new RespuestaComando<>(
				this.servicioDeRegistroDeSalida.registrarSalidaDelVehiculo(placa).getHoraDeSalida());
	}

}
