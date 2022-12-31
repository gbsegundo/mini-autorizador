package miniautorizador.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import miniautorizador.domain.request.CartaoRequest;
import miniautorizador.exception.ApiRestBaseException;
import miniautorizador.service.CartaoService;

@RestController
@RequestMapping( "/cartoes" )
@Tag( name = "Cartões", description = "Cadastro de Cartões" )
@Validated
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;
    
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "404", description = "Not Found") })
    @Operation(summary = "Retorna o saldo do cartão")
    @GetMapping( value = "/{numeroCartao}" )
    public ResponseEntity<BigDecimal> getCardByNumber(@ApiParam(
    	    name =  "numeroCartao",
    	    value = "Número do Cartão",
    	    required = true) @PathVariable String numeroCartao)  throws ApiRestBaseException {
    	
    	if(cartaoService.retornaSaldoCartao(numeroCartao) == null) {
    		return new ResponseEntity<BigDecimal>(HttpStatus.NOT_FOUND);
    	}else {
    		 return new ResponseEntity<BigDecimal>(cartaoService.retornaSaldoCartao(numeroCartao), HttpStatus.OK);
    	}       
    }

    
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"), @ApiResponse(responseCode = "422", description = "Unprocessable Entity") })
    @Operation(summary = "Cria um novo cartão")
    @PostMapping
    public ResponseEntity<Object> novoCartao( @Valid @RequestBody CartaoRequest request) throws ApiRestBaseException {
    	
    	if(cartaoService.validarCartaoExiste(request.getNumeroCartao())) {
    		 return new ResponseEntity<Object>(request, HttpStatus.UNPROCESSABLE_ENTITY);
    	} else {
    		 cartaoService.criarCartao(request);
    		 return new ResponseEntity<Object>(request, HttpStatus.CREATED);
    	}       
    }
}