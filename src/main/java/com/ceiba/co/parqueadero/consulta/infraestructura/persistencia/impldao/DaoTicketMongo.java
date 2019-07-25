package com.ceiba.co.parqueadero.consulta.infraestructura.persistencia.impldao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.co.parqueadero.consulta.dominio.dao.DaoTicket;
import com.ceiba.co.parqueadero.consulta.dominio.dto.Ticket;

@Repository
@Qualifier("daoTicketMongo")
public class DaoTicketMongo implements DaoTicket {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Ticket> listarVehiculosIngresados() {
		Query query = new Query();
		query.addCriteria(Criteria.where("horaDeSalida").is(null));
		return mongoTemplate.find(query, Ticket.class);
	}

}
