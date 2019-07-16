package com.ceiba.co.parqueadero.comando.aplicacion.entidad;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacionDeSalida {
	private LocalDateTime horaDeSalida;
	private Long totalAPagar;
}
