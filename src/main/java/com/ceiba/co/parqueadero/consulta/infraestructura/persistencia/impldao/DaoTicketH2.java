package com.ceiba.co.parqueadero.consulta.infraestructura.persistencia.impldao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ceiba.co.parqueadero.consulta.dominio.dao.DaoTicket;
import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;
import com.ceiba.co.parqueadero.consulta.infraestructura.persistencia.mapeo.TicketMapper;

@Repository
public class DaoTicketH2 implements DaoTicket {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Ticket> listarVehiculosIngresados() {
		return jdbcTemplate.query("SELECT * FROM ticket where horaDeSalida IS null", new TicketMapper());
	}

}
