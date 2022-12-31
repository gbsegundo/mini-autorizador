package miniautorizador.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransacaoRequest {

	private String numeroCartao;
	private String senhaCartao;
	private BigDecimal valor;

	public TransacaoRequest(TransacaoRequest transacaoRequest) {
		this.numeroCartao = transacaoRequest.getNumeroCartao();
		this.senhaCartao = transacaoRequest.getSenhaCartao();
		this.valor = transacaoRequest.getValor();
    }

}
