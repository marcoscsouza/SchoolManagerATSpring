package com.marcoscsouzads.SchoolManagerAT.application.controller;


import com.marcoscsouzads.SchoolManagerAT.domain.model.Professor;
import com.marcoscsouzads.SchoolManagerAT.domain.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    private Professor professor;
    @BeforeEach
    public void setUp() {
        professor = new Professor();
        professor.setNota(7.0);
    }

    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void saveProfessor() throws Exception {
        ResponseEntity<Professor> professorEntity = ResponseEntity.ok(professor);
        Mockito.when(professorService.save(Mockito.any(Professor.class))).thenReturn(professorEntity);

        String json = "{" +
                "\"aluno\":{\"id\":1}," +
                "\"disciplina\":{\"id\":1}," +
                "\"nota\":7.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/professores")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"aluno\":null," +
                        "\"disciplina\":null," +
                        "\"nota\":7.0}"));

    }

    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void findApprovedTest() throws Exception {
        List<Professor> professores = Collections.singletonList(professor);
        Mockito.when(professorService.findApproved(1L))
                .thenReturn(ResponseEntity.ok(professores));

        mockMvc.perform(MockMvcRequestBuilders.get("/professores/aprovados/{id}", 1L)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "\"aluno\":null," +
                        "\"disciplina\":null," +
                        "\"nota\":7.0}]"));


    }

    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void findDisapprovedTest() throws Exception {
        List<Professor> professores = Collections.singletonList(professor);
        Mockito.when(professorService.findDisapproved(1L))
                .thenReturn(ResponseEntity.ok(professores));

        mockMvc.perform(MockMvcRequestBuilders.get("/professores/reprovados/{id}", 1L)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{" +
                        "\"aluno\":null," +
                        "\"disciplina\":null," +
                        "\"nota\":7.0}]"));

    }
}
