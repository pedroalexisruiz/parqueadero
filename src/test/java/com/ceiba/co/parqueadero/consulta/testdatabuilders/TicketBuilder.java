package com.ceiba.co.parqueadero.consulta.testdatabuilders;

import java.time.LocalDateTime;

import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;

public class TicketBuilder {

	private static final String PLACA_BASE = "PED123";
	private static final Integer CILINDRAJE_BASE = 250;
	private LocalDateTime HORA_DE_ENTRADA_BASE = LocalDateTime.of(2019, 07, 01, 0, 0);
	private static final String TIPO_DE_VEHICULO = "MOTO";
	private static final String ID_BASE = "";
	
	private String id;
	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;
	private Long totalAPagar;
	private Integer cilindraje;

	public TicketBuilder() {
		this.id = ID_BASE;
		this.placa = PLACA_BASE;
		this.horaDeEntrada = HORA_DE_ENTRADA_BASE;
		this.horaDeSalida = null;
		this.cilindraje = CILINDRAJE_BASE;
		this.tipoDeVehiculo = TIPO_DE_VEHICULO;
	}

	public TicketBuilder conId(String id) {
		this.id = id;
		return this;
	}

	public TicketBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TicketBuilder conHoraDeEntrada(LocalDateTime horaDeEntrada) {
		this.horaDeEntrada = horaDeEntrada;
		return this;
	}

	public TicketBuilder conHoraDeSalida(LocalDateTime horaDeSalida) {
		this.horaDeSalida = horaDeSalida;
		return this;
	}

	public TicketBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public TicketBuilder conTipoDeVehiculo(String tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
		return this;
	}

	public TicketBuilder conTotalAPagar(Long totalAPagar) {
		this.totalAPagar = totalAPagar;
		return this;
	}

	public Ticket build() {
		return new Ticket(this.id, this.placa, this.horaDeEntrada, this.horaDeSalida, this.tipoDeVehiculo,
				this.totalAPagar, this.cilindraje);
	}
}
