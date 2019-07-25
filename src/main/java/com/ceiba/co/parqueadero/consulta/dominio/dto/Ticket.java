package com.ceiba.co.parqueadero.consulta.dominio.dto;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("tickets")
public class Ticket {

	private String id;
	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;
	private long totalAPagar;
	private Integer cilindraje;

	public Ticket() {
	}

	public Ticket(String id, String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida, String tipoDeVehiculo,
			long totalAPagar, Integer cilindraje) {
		this.id = id;
		this.placa = placa;
		this.horaDeEntrada = horaDeEntrada;
		this.horaDeSalida = horaDeSalida;
		this.tipoDeVehiculo = tipoDeVehiculo;
		this.totalAPagar = totalAPagar;
		this.cilindraje = cilindraje;
	}

	public String getId() {
		return id;
	}

	public String getPlaca() {
		return placa;
	}

	public LocalDateTime getHoraDeEntrada() {
		return horaDeEntrada;
	}

	public LocalDateTime getHoraDeSalida() {
		return horaDeSalida;
	}

	public String getTipoDeVehiculo() {
		return tipoDeVehiculo;
	}

	public long getTotalAPagar() {
		return totalAPagar;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
		this.horaDeEntrada = horaDeEntrada;
	}

}
