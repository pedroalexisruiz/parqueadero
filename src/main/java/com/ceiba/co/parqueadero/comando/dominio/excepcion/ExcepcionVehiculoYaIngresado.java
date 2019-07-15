package com.ceiba.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionVehiculoYaIngresado extends RuntimeException {

	public ExcepcionVehiculoYaIngresado(String errorMessage) {
		super(errorMessage);
	}
}