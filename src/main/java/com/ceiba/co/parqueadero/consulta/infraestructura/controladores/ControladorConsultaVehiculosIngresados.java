package com.ceiba.co.parqueadero.consulta.infraestructura.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;
import com.ceiba.co.parqueadero.consulta.aplicacion.manejadores.ManejadorListarVehiculosParqueados;

@RestController
@RequestMapping("/tickets")
public class ControladorConsultaVehiculosIngresados {

	private final ManejadorListarVehiculosParqueados manejadorListarVehiculosParqueados;

	@Autowired
	public ControladorConsultaVehiculosIngresados(
			ManejadorListarVehiculosParqueados manejadorListarVehiculosParqueados) {
		this.manejadorListarVehiculosParqueados = manejadorListarVehiculosParqueados;
	}

	@GetMapping
	public List<Ticket> listarVehiculosParqueados() {
		return manejadorListarVehiculosParqueados.ejecutar();
	}

}