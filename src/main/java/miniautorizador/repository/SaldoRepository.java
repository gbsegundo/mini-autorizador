package miniautorizador.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miniautorizador.entity.SaldoEntity;

@Repository
public interface SaldoRepository extends CrudRepository<SaldoEntity, Long > {

 
}
