package miniautorizador.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniautorizador.domain.request.CartaoRequest;
import miniautorizador.entity.CartaoEntity;
import miniautorizador.entity.SaldoEntity;
import miniautorizador.exception.ApiRestBaseException;
import miniautorizador.repository.CartaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	/**
	 * 
	 * @param numeroCartao
	 * @param valor
	 * @return
	 * @throws ApiRestBaseException
	 */
	public boolean validaSaldoCartao(String numeroCartao, BigDecimal valor) throws ApiRestBaseException {
        CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(numeroCartao).orElse(null);
        BigDecimal saldoCartao = cartaoEntity.getSaldoEntity().getValor();
        BigDecimal  diff = saldoCartao.subtract(valor);
        return diff.doubleValue() < 0.00 ? false: true;
      
    }
	
	
	/**
	 * 
	 * @param numeroCartao
	 * @param valor
	 * @throws ApiRestBaseException
	 */
	public void realizaTransacaoCartao(String numeroCartao, BigDecimal valor) throws ApiRestBaseException {
		 CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(numeroCartao).orElse(null);
		 BigDecimal saldoCartao = cartaoEntity.getSaldoEntity().getValor();
	     BigDecimal diff = saldoCartao.subtract(valor);
		 cartaoEntity.getSaldoEntity().setValor(diff);
		 cartaoRepository.save(cartaoEntity);
	}
		

}