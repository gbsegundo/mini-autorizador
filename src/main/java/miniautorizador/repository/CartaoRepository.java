package miniautorizador.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miniautorizador.entity.CartaoEntity;

@Repository
public interface CartaoRepository extends CrudRepository<CartaoEntity, Long > {

    Optional<CartaoEntity> findByNumeroCartao(String numeroCartao);
    
    Optional<CartaoEntity> findByNumeroCartaoAndSenha(String numeroCartao, String senha);
    
}