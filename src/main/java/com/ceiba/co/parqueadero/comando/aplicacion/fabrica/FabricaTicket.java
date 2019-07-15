package com.ceiba.co.parqueadero.comando.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.co.parqueadero.comando.aplicacion.entidad.ComandoTicket;
import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.entidad.TicketCarro;
import com.ceiba.co.parqueadero.comando.dominio.entidad.TicketMoto;

@Component
public class FabricaTicket {

	public Ticket crear(ComandoTicket ticketComando) {
		if (ticketComando.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			return new TicketMoto(ticketComando.getPlaca(), ticketComando.getTipoDeVehiculo(),
					ticketComando.getCilindraje());
		}
		return new TicketCarro(ticketComando.getPlaca(), ticketComando.getTipoDeVehiculo());
	}
}
