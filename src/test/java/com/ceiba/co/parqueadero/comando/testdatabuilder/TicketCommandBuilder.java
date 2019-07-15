package com.ceiba.co.parqueadero.comando.testdatabuilder;

import com.ceiba.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;

public class TicketCommandBuilder {

	private static final String PLACA_BASE = "PED123";
	private static final String TIPO_DE_VEHICULO_BASE = "CARRO";
	private static final int CILINDRAJE_BASE = 100;

	private String placa;
	private String tipoDeVehiculo;
	private int cilindraje;

	public TicketCommandBuilder() {
		this.cilindraje = CILINDRAJE_BASE;
		this.placa = PLACA_BASE;
		this.tipoDeVehiculo = TIPO_DE_VEHICULO_BASE;
	}

	public TicketCommandBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TicketCommandBuilder conTipoDeVehiculo(String tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
		return this;
	}

	public TicketCommandBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public ComandoTicket build() {
		return new ComandoTicket(this.placa, this.tipoDeVehiculo, this.cilindraje);
	}
}
