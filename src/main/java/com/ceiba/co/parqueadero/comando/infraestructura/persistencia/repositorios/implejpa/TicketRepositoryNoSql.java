package com.ceiba.co.parqueadero.comando.infraestructura.persistencia.repositorios.implejpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ceiba.co.parqueadero.comando.infraestructura.persistencia.entidad.TicketEntityNoSql;

public interface TicketRepositoryNoSql extends MongoRepository<TicketEntityNoSql, String> {

	TicketEntityNoSql findById(Long id);

	@Query("{ placa: ?0,horaDeSalida: null}")
	TicketEntityNoSql buscarPorPlacaSinSalida(String placa);

	Long countByTipoDeVehiculoAndHoraDeSalidaIsNull(String tipoDeVehiculo);

	@Query(value = "{ placa: ?0,horaDeSalida: {$exists: true}}", exists = true)
	boolean existeVehiculoEnParqueadero(String placa);
}
