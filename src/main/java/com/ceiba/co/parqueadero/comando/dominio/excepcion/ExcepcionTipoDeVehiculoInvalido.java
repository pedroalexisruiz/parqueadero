package com.ceiba.co.parqueadero.comando.dominio.excepcion;

public class ExcepcionTipoDeVehiculoInvalido extends RuntimeException{

	public ExcepcionTipoDeVehiculoInvalido(String message) {
		super(message);
	}
}