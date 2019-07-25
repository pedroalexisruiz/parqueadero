package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.entidad;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("tickets")
public class TicketEntityNoSql {
	
	@Id
	private String id;
	private String placa;
	private LocalDateTime horaDeEntrada;
	private LocalDateTime horaDeSalida;
	private String tipoDeVehiculo;
	private Integer cilindraje;
	private long totalAPagar;
}
