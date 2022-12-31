package miniautorizador.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import miniautorizador.domain.request.TransacaoRequest;
import miniautorizador.exception.ApiRestBaseException;
import miniautorizador.service.CartaoService;
import miniautorizador.service.TransacaoService;

@RestController
@RequestMapping( "/transacoes" )
@Tag( name = "Transações", description = "Transação de Cartões" )
@Validated
public class TransacaoController {

    @Autowired
    private CartaoService cartaoService;
       
    @Autowired
    private TransacaoService transacaoService;
    
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "OK"), @ApiResponse(responseCode = "422", description = "SALDO_INSUFICIENTE")
    		, @ApiResponse(responseCode = "422", description = "SENHA_INVALIDA") , @ApiResponse(responseCode = "422", description = "CARTAO_INEXISTENTE")})
    @Operation(summary = "Realiza uma transação")
    @PostMapping
    public ResponseEntity<Object> transacaoCartao( @Valid @RequestBody TransacaoRequest request) throws ApiRestBaseException {
    	
    	if(!cartaoService.validarCartaoExiste(request.getNumeroCartao())) {
    		 return new ResponseEntity<Object>("CARTAO_INEXISTENTE", HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	else if(!cartaoService.validarSenhaCartao(request.getNumeroCartao(), request.getSenhaCartao())) {
   		     return new ResponseEntity<Object>("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    	else if(!transacaoService.validaSaldoCartao(request.getNumeroCartao(), request.getValor())) {
    		 return new ResponseEntity<Object>("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
    	}else {
    		transacaoService.realizaTransacaoCartao(request.getNumeroCartao(), request.getValor());
    		return new ResponseEntity<Object>("OK", HttpStatus.CREATED);
    	}
    
    	   
    }
}