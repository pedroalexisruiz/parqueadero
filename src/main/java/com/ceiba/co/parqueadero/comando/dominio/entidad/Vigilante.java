package com.ceiba.co.parqueadero.comando.dominio.entidad;

import java.time.LocalDateTime;
import java.util.Calendar;

import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionDiaNoHabil;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionParqueaderoSinEspacio;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionVehiculoYaIngresado;
import com.ceiba.co.parqueadero.comando.dominio.repositorio.RepositorioTicket;
import com.ceiba.co.parqueadero.comando.dominio.servicio.ServicioMarcarSalida;
import com.ceiba.co.parqueadero.comando.dominio.servicio.ServicioRegistrarEntrada;

public class Vigilante implements ServicioRegistrarEntrada, ServicioMarcarSalida {

	private static final int NUMERO_MAXIMO_CARROS = 20;
	private static final int NUMERO_MAXIMO_MOTOS = 10;

	private static final char PRIMERA_LETRA_PLACA_NO_VALIDA = 'A';
	public static final String DIA_NO_HABIL = "No se permite el ingreso del vehiculo en dia no habil";

	public static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo ya se encuentra en el parqueadero.";
	public static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehiculo no se encuentra en el parqueadero.";
	public static final String NO_HAY_ESPACIOS_DISPONIBLES = "No hay espacios disponibles en el parqueadero.";

	private final RepositorioTicket repositorioDeTickets;
	private final GeneradorDeFecha generadorDeFecha;

	public Vigilante(RepositorioTicket repositorioDeTickets, GeneradorDeFecha generadorDeFecha) {
		super();
		this.repositorioDeTickets = repositorioDeTickets;
		this.generadorDeFecha = generadorDeFecha;
	}

	public LocalDateTime registrarEntradaDeVehiculo(Ticket ticket) {
		verificarPlacasRestringidas(ticket.getPlaca());
		verificarDisponibilidad(ticket.getTipoDeVehiculo());
		verificarVehiculoDentro(ticket);
		ticket.setHoraDeEntrada(generadorDeFecha.obtenerHoraLocalActual());

		return registrarEntrada(ticket);
	}

	public void verificarPlacasRestringidas(String placa) {
		char primeraLetra = placa.charAt(0);
		Calendar calendario = generadorDeFecha.obtenerFechaActual();

		if (primeraLetra == PRIMERA_LETRA_PLACA_NO_VALIDA && !esDiaHabil(calendario.get(Calendar.DAY_OF_WEEK))) {
			throw new ExcepcionDiaNoHabil(DIA_NO_HABIL);
		}

	}

	private boolean esDiaHabil(int dia) {
		return dia != Calendar.MONDAY && dia != Calendar.SUNDAY;
	}

	public void verificarDisponibilidad(String tipoDeVehiculo) {
		long cantidadOcupada = contarVehiculosParqueadosPorTipo(tipoDeVehiculo);

		switch (tipoDeVehiculo) {
		case Ticket.MOTO:
			verificarDisponibilidadDeMotos(cantidadOcupada);
			break;
		case Ticket.CARRO:
			verificarDisponibilidadDeCarros(cantidadOcupada);
			break;
		default:
			break;
		}
	}

	public LocalDateTime registrarEntrada(Ticket ticket) {
		return this.repositorioDeTickets.registrarEntrada(ticket);
	}

	public long contarVehiculosParqueadosPorTipo(String tipoDeVehiculo) {
		Long cantidad = this.repositorioDeTickets.contarVehiculosParqueadosPorTipo(tipoDeVehiculo);
		return cantidad == null ? 0 : cantidad.longValue();
	}

	private void verificarDisponibilidadDeMotos(long cantidadOcupada) {
		if (cantidadOcupada >= NUMERO_MAXIMO_MOTOS) {
			throw new ExcepcionParqueaderoSinEspacio(NO_HAY_ESPACIOS_DISPONIBLES);
		}
	}

	private void verificarDisponibilidadDeCarros(long cantidadOcupada) {
		if (cantidadOcupada >= NUMERO_MAXIMO_CARROS) {
			throw new ExcepcionParqueaderoSinEspacio(NO_HAY_ESPACIOS_DISPONIBLES);
		}
	}

	private void verificarVehiculoDentro(Ticket ticket) {
		boolean exists = existeVehiculoEnParqueadero(ticket.getPlaca());
		if (exists) {
			throw new ExcepcionVehiculoYaIngresado(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}

	public boolean existeVehiculoEnParqueadero(String placa) {
		return this.repositorioDeTickets.existeVehiculoEnParqueadero(placa);
	}

	@Override
	public Ticket registrarSalidaDelVehiculo(String plate) {
		Ticket ticket = this.repositorioDeTickets.buscarPorPlacaSinSalida(plate);
		if (ticket == null) {
			throw new ExcepcionVehiculoYaIngresado(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		ticket.setHoraDeSalida(generadorDeFecha.obtenerHoraLocalActual());
		ticket.calcularPrecioAPagar();
		this.repositorioDeTickets.registrarSalida(ticket);
		return ticket;
	}
}
