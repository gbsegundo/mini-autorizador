package miniautorizador.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniautorizador.domain.request.CartaoRequest;
import miniautorizador.entity.CartaoEntity;
import miniautorizador.entity.SaldoEntity;
import miniautorizador.exception.ApiRestBaseException;
import miniautorizador.repository.CartaoRepository;
import miniautorizador.repository.SaldoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	/**
	 * 
	 * @param numeroCartao
	 * @return
	 * @throws ApiRestBaseException
	 */
	public boolean validarCartaoExiste(String numeroCartao) throws ApiRestBaseException {
		return cartaoRepository.findByNumeroCartao(numeroCartao).isPresent();
	}
	
	/**
	 * 	
	 * @param senha
	 * @return
	 * @throws ApiRestBaseException
	 */
	public boolean validarSenhaCartao(String numeroCartao, String senha) throws ApiRestBaseException {
		return cartaoRepository.findByNumeroCartaoAndSenha(numeroCartao, senha).isPresent();
	}
		
	/**
	 * 
	 * @param numeroCartao
	 * @return
	 */
	public BigDecimal retornaSaldoCartao(String numeroCartao) throws ApiRestBaseException {
        CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(numeroCartao).orElse(null);
        if ( cartaoEntity != null ) {
        	try {
        		return cartaoEntity.getSaldoEntity().getValor();
        	}catch (Exception e) {
				return BigDecimal.valueOf(0);
			}
        	
        }else {
        	return null;
        }
      
    }

	
	/**
	 * 
	 * @param cartaoRequest
	 * @return
	 * @throws ApiRestBaseException
	 */
	public CartaoEntity criarCartao(CartaoRequest cartaoRequest) throws ApiRestBaseException {
		
		//gera o saldo de 500.00 para o novo cartao
		SaldoEntity saldoEntity = new SaldoEntity();
		saldoEntity.setValor(BigDecimal.valueOf(500));
		saldoRepository.save(saldoEntity);
		
		//Cria o cartao novo
		CartaoEntity cartaoEntity = new CartaoEntity();
		cartaoEntity.setNumeroCartao(cartaoRequest.getNumeroCartao());
		cartaoEntity.setSenha(cartaoRequest.getSenha());
		cartaoEntity.setSaldoEntity(saldoEntity);
		return cartaoRepository.save(cartaoEntity);
	}
}