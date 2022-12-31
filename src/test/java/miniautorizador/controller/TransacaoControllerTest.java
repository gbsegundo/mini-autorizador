package miniautorizador.controller;

import miniautorizador.ApplicationTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransacaoControllerTest extends ApplicationTests {

    private static final String URL_API = "/transacoes";

    private MockMvc mockMvc;

    @Autowired
    private TransacaoController transacaoController;

    @Autowired
    private CartaoController cartaoController;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
    }

    @Test
    @DisplayName("Cria a Transação")
    public void testGeraTranscao() throws Exception {
        String cartao = "{\"numeroCartao\": \"11111111111\", \"senha\": \"222222222\"}";

        this.mockMvc = MockMvcBuilders.standaloneSetup(cartaoController).build();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cartao)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String transacao = "{\"numeroCartao\": \"11111111111\", \"senha\": \"222222222\", \"valor\": \"10.20\"}";

        this.mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transacao)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

  
}
