package com.example.agitask;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
     * ================
     * TESTES - SUCESSO
     * ================
     */

    @Test
    @Transactional
    public void deveCriarUmTime() throws Exception {
        String json = """
                    {
                    }
                """;

        var result = mockMvc.perform(post("/times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTime").exists())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("---> Retorno: " + responseBody);
        String idTime = objectMapper.readTree(responseBody).get("idTime").asText();
        System.out.println("---> ID extraído: " + idTime);
    }

    @Test
    @Transactional
    public void deveCriarUmTimeEBuscar() throws Exception {
        String json = """
                    {
                    }
                """;

        var result = mockMvc.perform(post("/times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTime").exists())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("---> Retorno: " + responseBody);
        String idTime = objectMapper.readTree(responseBody).get("idTime").asText();
        System.out.println("---> ID extraído: " + idTime);

        mockMvc.perform(get("/times/" + idTime))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void deveCriarMultiplosTimesEBuscarALista() throws Exception {
        for (int i = 0; i < 10; i++) {
            String json = """
                        {
                        }
                    """;

            mockMvc.perform(post("/times")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.idTime").exists())
                    .andReturn();
        }

        mockMvc.perform(get("/times"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void deveCriarUmTimeEDeletar() throws Exception {
        String json = """
                    {
                    }
                """;

        var result1 = mockMvc.perform(post("/times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTime").exists())
                .andReturn();

        String responseBody1 = result1.getResponse().getContentAsString();
        System.out.println("---> Retorno: " + responseBody1);
        String idTime = objectMapper.readTree(responseBody1).get("idTime").asText();
        System.out.println("---> ID extraído: " + idTime);

        var result2 = mockMvc.perform(delete("/times/" + idTime))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody2 = result2.getResponse().getContentAsString();
        System.out.println("---> Retorno: " + responseBody2);
    }

    /*
     * ==============
     * TESTES - FALHA
     * ==============
     */

    @Test
    @Transactional
    public void naoDeveCriarUmTimeSemDadosDeEntrada() throws Exception {
        var result = mockMvc.perform(post("/times"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
