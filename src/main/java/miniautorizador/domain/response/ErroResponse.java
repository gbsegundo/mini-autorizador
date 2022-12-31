package miniautorizador.domain.response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErroResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String COD_NOK = "NOK";

	private String codigo;
	private String mensagem;
	private List<String> errors;

    public ErroResponse(List<String> errors) {
    	this.errors = errors;
    }

    public ErroResponse(String codigo, List<String> errors) {
    	this.codigo = codigo;
    	this.errors = errors;
    }

    public ErroResponse(String mensagem) {
    	this.mensagem = mensagem;
    	if(!mensagem.isBlank()) {
    		this.errors = Arrays.asList(mensagem);
        }
    }

	public ErroResponse(String codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		if(!mensagem.isBlank()) {
    		this.errors = Arrays.asList(mensagem);
        }
	}
}
