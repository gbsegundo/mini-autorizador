package miniautorizador.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import miniautorizador.ApplicationTests;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartaoControllerTest extends ApplicationTests {

    private static final String URL_API = "/cartoes";

    private MockMvc mockMvc;

    @Autowired
    private CartaoController cartaoController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(cartaoController).build();
    }

    @Test
    @DisplayName("Cria o Cartão")
    public void testNovoCartao() throws Exception {
        String data = "{\"numeroCartao\": \"1010101010\", \"senha\": \"222222222\"}";

       this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON))
        		.andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    @DisplayName("Pega o Cartão por Número do Cartão")
    public void testRetornaCartao() throws Exception {
    
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API+"/1010101010")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

      
    }

 
}
