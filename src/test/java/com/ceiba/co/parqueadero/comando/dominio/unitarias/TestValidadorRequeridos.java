package com.ceiba.co.parqueadero.comando.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.entidad.ValidadorRequeridos;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionObjetoRequerido;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionStringRequerido;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionTipoDeVehiculoInvalido;

public class TestValidadorRequeridos {

	@Test
	public void validarStringVacio() {
		// arrange
		String cadena = "";

		// act
		try {
			ValidadorRequeridos.validarStringNoNuloOVacio(cadena, Ticket.PLACA_VACIA);
			fail();
		} catch (ExcepcionStringRequerido e) {
			// assert
			assertEquals(e.getMessage(), Ticket.PLACA_VACIA);
		}
	}
	
	@Test
	public void validarStringNulo() {
		// arrange
		String cadena = null;

		// act
		try {
			ValidadorRequeridos.validarStringNoNuloOVacio(cadena, Ticket.PLACA_VACIA);
			fail();
		} catch (ExcepcionStringRequerido e) {
			// assert
			assertEquals(e.getMessage(), Ticket.PLACA_VACIA);
		}
	}
	
	@Test
	public void validarObjetoNulo() {
		// arrange
		String cadena = null;

		// act
		try {
			ValidadorRequeridos.validarObjetoNoNulo(cadena, Ticket.TIPO_VEHICULO_VACIO);
			fail();
		} catch (ExcepcionObjetoRequerido e) {
			// assert
			assertEquals(e.getMessage(), Ticket.TIPO_VEHICULO_VACIO);
		}
	}
	
	@Test
	public void validarTipoDeVehiculoNulo() {
		// arrange
		String cadena = null;

		// act
		try {
			ValidadorRequeridos.validarTipoDeVehiculo(cadena, Ticket.TIPO_VEHICULO_INVALIDO);
			fail();
		} catch (ExcepcionTipoDeVehiculoInvalido e) {
			// assert
			assertEquals(e.getMessage(), Ticket.TIPO_VEHICULO_INVALIDO);
		}
	}
	
	@Test
	public void validarTipoDeVehiculoNoValido() {
		// arrange
		String cadena = "BUS";

		// act
		try {
			ValidadorRequeridos.validarTipoDeVehiculo(cadena, Ticket.TIPO_VEHICULO_INVALIDO);
			fail();
		} catch (ExcepcionTipoDeVehiculoInvalido e) {
			// assert
			assertEquals(e.getMessage(), Ticket.TIPO_VEHICULO_INVALIDO);
		}
	}
}
