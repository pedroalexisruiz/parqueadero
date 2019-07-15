package com.ceiba.co.parqueadero.comando.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.co.parqueadero.comando.dominio.entidad.GeneradorDeFecha;
import com.ceiba.co.parqueadero.comando.dominio.entidad.Vigilante;
import com.ceiba.co.parqueadero.comando.dominio.repositorio.RepositorioTicket;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios.RepositorioTicketH2;

@Configuration
public class ServicioBean {

	@Bean
	public Vigilante crearServicioDeTickets(RepositorioTicket repositorioDeTickets, GeneradorDeFecha generadorDeFechas) {
		return new Vigilante(repositorioDeTickets, generadorDeFechas);
	}
	
	@Bean
	public GeneradorDeFecha crearGeneradorDeFechas() {
		return GeneradorDeFecha.obtenerInstancia();
	}
	
	@Bean
	public RepositorioTicket crearRepositorio() {
		return new RepositorioTicketH2();
	}
}
