package com.ceiba.co.parqueadero.consulta.controladores.integracion;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


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
}
