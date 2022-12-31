package miniautorizador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import miniautorizador.ApplicationTests;
import miniautorizador.domain.request.CartaoRequest;
import miniautorizador.entity.CartaoEntity;
import miniautorizador.entity.SaldoEntity;
import miniautorizador.repository.CartaoRepository;

public class CartaoServiceTest extends ApplicationTests {

    @Autowired
    private CartaoService cartaoService;

    @MockBean
    private CartaoRepository cartaoRepository;

    private SaldoEntity saldo = new SaldoEntity();

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("Cria o Cartão com sucesso")
    void testSaveCartao()  throws Exception {
    	CartaoRequest mockCartaoRequest = new CartaoRequest();
    	mockCartaoRequest.setNumeroCartao("1111111111");
    	mockCartaoRequest.setSenha("123");
     
    	CartaoEntity cartaoEntity = mapper.map(mockCartaoRequest, CartaoEntity.class);

        when(cartaoRepository.save(any(CartaoEntity.class))).thenReturn(cartaoEntity);

        CartaoEntity saveCartaoModel = cartaoService.criarCartao(mockCartaoRequest);
        CartaoEntity criaCartaoModel = mapper.map(saveCartaoModel, CartaoEntity.class);

        Assertions.assertNotNull(cartaoEntity);
        assertEquals(mockCartaoRequest, criaCartaoModel);
    }

  

    @Test
    @DisplayName("Localiza o Cartão por Número do Cartão com sucesso")
    void testFindCartaoByNumber()   throws Exception {
        CartaoEntity mockCartaoEntity = CartaoEntity.builder()
                .numeroCartao("1111111111")
                .build();

        when(cartaoRepository.findByNumeroCartao("1111111111")).thenReturn(Optional.of(mockCartaoEntity));

        BigDecimal findCartao = cartaoService.retornaSaldoCartao("1111111111");

        Assertions.assertNotNull(findCartao);
        assertEquals(mockCartaoEntity.getSaldoEntity().getValor(), findCartao);
    }

       
}
