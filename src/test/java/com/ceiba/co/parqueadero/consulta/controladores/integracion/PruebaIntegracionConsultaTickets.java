package com.ceiba.co.parqueadero.consulta.controladores.integracion;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;
import com.ceiba.co.parqueadero.consulta.testdatabuilders.TicketBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ComponentScan("ceiba.com.co.parqueadero")
public class PruebaIntegracionConsultaTickets {

	private MockMvc mvc;
	private static final String URL = "/tickets";

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void probarListadoParqueaderoVacio() {
		// arrange
		ResultActions resultados;
		String respuesta = "[]";

		try {
			// act
			resultados = mvc
					.perform(get(URL).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// assert
			resultados.andExpect(content().string(respuesta));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Sql("/insertarCarros.sql")
	public void probarListadoParqueadero() {
		// arrange
		ResultActions resultados;
		int cantidadVehiculosParqueados = 20;
		Ticket ticket = new TicketBuilder().conPlaca("YBC123").conTipoDeVehiculo("CARRO").conTotalAPagar(0L).build();

		try {
			// act
			resultados = mvc
					.perform(get(URL).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// assert
			resultados.andExpect(jsonPath("$", hasSize(cantidadVehiculosParqueados)));
			resultados.andExpect(jsonPath("$[19].placa", is(ticket.getPlaca())))
					.andExpect(jsonPath("$[19].tipoDeVehiculo", is(ticket.getTipoDeVehiculo())));
		} catch (Exception e) {
			fail();
		}
	}
}
