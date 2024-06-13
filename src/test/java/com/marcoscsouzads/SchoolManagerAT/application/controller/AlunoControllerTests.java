package com.marcoscsouzads.SchoolManagerAT.application.controller;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import com.marcoscsouzads.SchoolManagerAT.domain.service.AlunoService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;


    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void findAllAlunosTest() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno");
        aluno.setEmail("aluno@gmail.com");
        aluno.setTelefone("123456");
        aluno.setEndereco("endereco");
        aluno.setCpf("12345678");
        List<Aluno> alunos = Collections.singletonList(aluno);
        Mockito.when(alunoService.findAll()).thenReturn(ResponseEntity.ok(alunos));

        mockMvc.perform(MockMvcRequestBuilders.get("/alunos")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json
                        ("[{" +
                                "\"nome\":\"Aluno\"," +
                                "\"cpf\":\"12345678\"," +
                                "\"email\":\"aluno@gmail.com\"," +
                                "\"telefone\":\"123456\"," +
                                "\"endereco\":\"endereco\"}]"));

    }

    @Test
    @WithMockUser(username = "teste", roles = {"USER"})
    public void saveAlunoTest() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno");
        aluno.setEmail("aluno@gmail.com");
        aluno.setTelefone("123456");
        aluno.setEndereco("endereco");
        aluno.setCpf("12345678");
        ResponseEntity<Aluno> alunoResponseEntity = ResponseEntity.ok(aluno);
        when(alunoService.save(Mockito.any(Aluno.class))).thenReturn(alunoResponseEntity);

        String json = "{" +
                "\"nome\":\"Aluno\"," +
                "\"cpf\":\"12345678\"," +
                "\"email\":\"aluno@gmail.com\"," +
                "\"telefone\":\"123456\"," +
                "\"endereco\":\"endereco\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "\"nome\":\"Aluno\"," +
                        "\"cpf\":\"12345678\"," +
                        "\"email\":\"aluno@gmail.com\"," +
                        "\"telefone\":\"123456\"," +
                        "\"endereco\":\"endereco\"}"));

    }

}
