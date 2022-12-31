package miniautorizador.domain.request;

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
public class CartaoRequest {

	private String numeroCartao;
	private String senha;

	public CartaoRequest(CartaoRequest cartaoRequest) {
		this.numeroCartao = cartaoRequest.getNumeroCartao();
		this.senha = cartaoRequest.getSenha();
    }

}
