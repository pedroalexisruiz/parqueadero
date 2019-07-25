package com.ceiba.co.parqueadero.comando.dominio.entidad;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketMoto extends Ticket {

	private Integer cilindraje;

	private static final String CILINDRAJE_MOTO_VACIO = "Debes ingresar el cilindraje de la moto";
	public static final Long VALOR_HORA = 500L;
	public static final Long VALOR_DIA = 4000L;
	public static final int CILINDRAJE_ALTO = 500;
	public static final int COSTO_CILINDRAJE_ALTO = 2000;

	@Override
	public void calcularPrecioAPagar() {
		long totalAPagar = 0;
		int horasDeUso = super.calcularHorasDeParqueo();

		if (horasDeUso >= LIMITE_COBRO_POR_HORAS) {
			totalAPagar = super.obtenerValorPorDias(horasDeUso, VALOR_DIA, VALOR_HORA);
		} else {
			totalAPagar = super.obtenerValorPorHoras(horasDeUso, VALOR_HORA);
		}
		if (this.cilindraje >= CILINDRAJE_ALTO) {
			totalAPagar += COSTO_CILINDRAJE_ALTO;
		}
		this.setTotalAPagar(totalAPagar);
	}

	public TicketMoto(String plate, String vehicleType, Integer cilindraje) {
		super(plate, vehicleType);
		ValidadorRequeridos.validarObjetoNoNulo(cilindraje, CILINDRAJE_MOTO_VACIO);
		this.cilindraje = cilindraje;
	}

	public TicketMoto(String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida, String tipoDeVehiculo,
			Integer cilindraje) {
		super(null, placa, horaDeEntrada, horaDeSalida, tipoDeVehiculo, 0);
		ValidadorRequeridos.validarObjetoNoNulo(cilindraje, CILINDRAJE_MOTO_VACIO);
		this.cilindraje = cilindraje;
	}

	public TicketMoto(String id, String placa, LocalDateTime horaDeEntrada, LocalDateTime horaDeSalida,
			String tipoDeVehiculo, Long totalAPagar, Integer cilindraje) {
		super(id, placa, horaDeEntrada, horaDeSalida, tipoDeVehiculo, totalAPagar);
		ValidadorRequeridos.validarObjetoNoNulo(cilindraje, CILINDRAJE_MOTO_VACIO);
		this.cilindraje = cilindraje;
	}

}
