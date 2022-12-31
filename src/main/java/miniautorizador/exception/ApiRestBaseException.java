package miniautorizador.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import miniautorizador.domain.response.ErroResponse;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiRestBaseException extends Exception {
	
	private HttpStatus status;
	private ErroResponse erroResponse;

	public ApiRestBaseException(String msg) {
		super(msg);
		this.erroResponse = new ErroResponse(null, msg);
	}
	
	public ApiRestBaseException(ErroResponse erroResponse) {
		this.erroResponse = erroResponse;
	}
	
	public ApiRestBaseException(List<String> listaMsg) {
		super(listaMsg.stream().collect(Collectors.joining(";")));
		this.erroResponse = new ErroResponse(listaMsg);
	}

	public ApiRestBaseException(Exception ex) {
		super(ex);
	}

	public ApiRestBaseException(String msg, Exception ex) {
		super(msg, ex);
	}

	public ApiRestBaseException(String codigo, String msg) {
		super(msg);
		this.erroResponse = new ErroResponse(codigo, msg);
	}
	
	public ApiRestBaseException(HttpStatus codigo, String msg) {
		super(msg);
		this.erroResponse = new ErroResponse(String.valueOf(codigo.value()), msg);
	}
	
	public ApiRestBaseException(HttpStatus status, String codigo, String msg) {
		super(msg);
		this.status = status;
		this.erroResponse = new ErroResponse(codigo, msg);
	}

}
