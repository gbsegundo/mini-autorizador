package miniautorizador.domain.response;

import lombok.Data;

@Data
public class ApiRestBaseResponse {

	private String resposta;

	public ApiRestBaseResponse() {
	}

	public ApiRestBaseResponse(String resposta) {
		this.resposta = resposta;
	}
}
