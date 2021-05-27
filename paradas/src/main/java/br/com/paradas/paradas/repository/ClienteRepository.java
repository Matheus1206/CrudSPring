package br.com.paradas.paradas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.paradas.paradas.model.Cliente;
import br.com.paradas.paradas.model.Status;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	List<Cliente> findByStatus(Status valueOf);

	
}
