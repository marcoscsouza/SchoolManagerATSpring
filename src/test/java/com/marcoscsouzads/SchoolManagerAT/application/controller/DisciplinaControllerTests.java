package com.marcoscsouzads.SchoolManagerAT.application.controller;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.DisciplinaRepository;
import com.marcoscsouzads.SchoolManagerAT.domain.service.DisciplinaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DisciplinaController.class)
public class DisciplinaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void findAllDisciplinasTest() throws Exception {
        Disciplina testDisciplina = new Disciplina();
        testDisciplina.setNome("Test");
        testDisciplina.setCodigo("codigoTest");
        List<Disciplina> disciplinas = Collections.singletonList(testDisciplina);
        Mockito.when(disciplinaService.findAll()).thenReturn(ResponseEntity.ok(disciplinas));

        mockMvc.perform(MockMvcRequestBuilders.get("/disciplinas")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                                "\"nome\":\"Test\"," +
                                "\"codigo\":\"codigoTest\"}]"));

    }




    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void saveDisciplinaTest() throws Exception {
        Disciplina testDisciplina = new Disciplina();
        testDisciplina.setNome("Test");
        testDisciplina.setCodigo("codigoTest");
        ResponseEntity<Disciplina> responseEntity = ResponseEntity.ok(testDisciplina);
        Mockito.when(disciplinaService.save(Mockito.any(Disciplina.class))).thenReturn(responseEntity);

        String json = "{" +
                "\"nome\":\"Test\"," +
                "\"codigo\":\"codigoTest\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/disciplinas")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"nome\":\"Test\"," +
                        "\"codigo\":\"codigoTest\"}"));


    }
}
