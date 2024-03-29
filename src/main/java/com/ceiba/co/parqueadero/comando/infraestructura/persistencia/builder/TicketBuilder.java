package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.builder;

import org.springframework.stereotype.Component;

import com.ceiba.co.parqueadero.comando.dominio.entidad.Ticket;
import com.ceiba.co.parqueadero.comando.dominio.entidad.TicketCarro;
import com.ceiba.co.parqueadero.comando.dominio.entidad.TicketMoto;
import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.entidad.EntidadTicket;

@Component
public class TicketBuilder {

	public EntidadTicket convertirAEntidad(Ticket ticket) {
		if(ticket == null) {
			return null;
		}
		if (ticket instanceof TicketMoto) {
			return new EntidadTicket(Long.parseLong(ticket.getId()), ticket.getPlaca(), ticket.getHoraDeEntrada(),
					ticket.getHoraDeSalida(), ticket.getTipoDeVehiculo(), ((TicketMoto) ticket).getCilindraje(),
					ticket.getTotalAPagar());
		}
		return new EntidadTicket(Long.parseLong(ticket.getId()), ticket.getPlaca(), ticket.getHoraDeEntrada(), ticket.getHoraDeSalida(),
				ticket.getTipoDeVehiculo(), null, ticket.getTotalAPagar());
	}

	public Ticket convertirADominio(EntidadTicket entidadTicket) {
		if(entidadTicket == null) {
			return null;
		}
		if (entidadTicket.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			new TicketMoto(entidadTicket.getId().toString(), entidadTicket.getPlaca(), entidadTicket.getHoraDeEntrada(),
					entidadTicket.getHoraDeSalida(), entidadTicket.getTipoDeVehiculo(), entidadTicket.getTotalAPagar(),
					entidadTicket.getCilindraje());
		}
		return new TicketCarro(entidadTicket.getId().toString(), entidadTicket.getPlaca(), entidadTicket.getHoraDeEntrada(),
				entidadTicket.getHoraDeSalida(), entidadTicket.getTipoDeVehiculo(), entidadTicket.getTotalAPagar());
	}
}
