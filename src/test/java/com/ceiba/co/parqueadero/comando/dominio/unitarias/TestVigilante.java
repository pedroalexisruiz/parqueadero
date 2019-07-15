package com.ceiba.co.parqueadero.comando.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import com.ceiba.co.parqueadero.comando.dominio.entidad.GeneradorDeFecha;
import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.entidad.Vigilante;
import com.ceiba.co.parqueadero.comando.dominio.excepcion.ExcepcionDiaNoHabil;
import com.ceiba.co.parqueadero.comando.testdatabuilder.TicketCarroBuilder;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan("ceiba.com.co")
public class TestVigilante {

	@Mock
	GeneradorDeFecha generadorDeFecha;

	@Spy
	@InjectMocks
	Vigilante vigilante;

	@Before
	public void setUp() {
		//
	}

	@Test
	public void registrarCarroEnDiaHabil() {
		// arrange
		LocalDateTime fechaDeIngreso = LocalDateTime.of(2019, 7, 11, 12, 0, 0);
		String placa = "ABC078";
		Ticket carro = new TicketCarroBuilder().conPlaca(placa).conHoraDeEntrada(fechaDeIngreso).build();
		Calendar fechaActual = Calendar.getInstance();
		LocalDateTime resultado;

		fechaActual.setTime(Date.from(fechaDeIngreso.atZone(ZoneId.systemDefault()).toInstant()));

		doReturn(0L).when(vigilante).contarVehiculosParqueadosPorTipo(carro.getTipoDeVehiculo());
		doReturn(false).when(vigilante).existeVehiculoEnParqueadero(carro.getPlaca());
		doReturn(fechaDeIngreso).when(vigilante).registrarEntrada(carro);

		when(generadorDeFecha.obtenerFechaActual()).thenReturn(fechaActual);
		when(generadorDeFecha.obtenerHoraLocalActual()).thenReturn(
				Instant.ofEpochMilli(fechaActual.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		// act
		resultado = vigilante.registrarEntradaDeVehiculo(carro);

		// assert
		assertEquals(fechaDeIngreso, resultado);
	}

	@Test
	public void registrarCarroEnDiaNoHabil() {
		// arrange
		LocalDateTime fechaDeIngreso = LocalDateTime.of(2019, 07, 7, 12, 0, 0);
		String placa = "ABC078";
		Ticket carro = new TicketCarroBuilder().conPlaca(placa).conHoraDeEntrada(fechaDeIngreso).build();

		Calendar fechaActual = Calendar.getInstance();
		fechaActual.setTime(Date.from(fechaDeIngreso.atZone(ZoneId.systemDefault()).toInstant()));

		when(generadorDeFecha.obtenerFechaActual()).thenReturn(fechaActual);
		// act
		try {
			vigilante.registrarEntradaDeVehiculo(carro);
			fail();
		} catch (ExcepcionDiaNoHabil e) {
			// assert
			assertEquals(Vigilante.DIA_NO_HABIL, e.getMessage());
		}
	}
}
