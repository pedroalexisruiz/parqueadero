package com.ceiba.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionParqueaderoSinEspacio extends RuntimeException {

	public ExcepcionParqueaderoSinEspacio(String errorMessage) {
		super(errorMessage);
	}
}