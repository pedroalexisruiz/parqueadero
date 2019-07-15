package com.ceiba.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionStringRequerido extends RuntimeException{

	public ExcepcionStringRequerido(String message) {
		super(message);
	}
}
