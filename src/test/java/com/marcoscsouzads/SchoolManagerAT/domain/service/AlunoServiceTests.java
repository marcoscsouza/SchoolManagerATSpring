package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AlunoServiceTests {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    public void findAllAlunosTest() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno");
        aluno.setEmail("aluno@gmail.com");
        aluno.setTelefone("123456");
        aluno.setCpf("12312313123");
        aluno.setEndereco("av. dores");
        Mockito.when(alunoRepository.findAll()).thenReturn(Collections.singletonList(aluno));
        ResponseEntity<List<Aluno>> result = alunoService.findAll();
        List<Aluno> alunos = result.getBody();

        if (!alunos.isEmpty()) {
            for (Aluno aluno1 : alunos) {
                assertEquals(aluno.getNome(), aluno1.getNome());
                assertEquals(aluno.getEmail(), aluno1.getEmail());
                assertEquals(aluno.getTelefone(), aluno1.getTelefone());
                assertEquals(aluno.getCpf(), aluno1.getCpf());
                assertEquals(aluno.getEndereco(), aluno1.getEndereco());
            }
        }

    }

    @Test
    public void SaveAlunoTest() {

        Aluno aluno = new Aluno();
        aluno.setNome("Aluno");
        aluno.setEmail("aluno@gmail.com");
        aluno.setTelefone("123456");
        aluno.setCpf("12312313123");
        aluno.setEndereco("av. dores");

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
        ResponseEntity<Aluno> result = alunoService.save(aluno);
        Aluno alunoSaved = result.getBody();

        assertEquals(aluno.getNome(), alunoSaved.getNome());
        assertEquals(aluno.getEmail(), alunoSaved.getEmail());
        assertEquals(aluno.getTelefone(), alunoSaved.getTelefone());
        assertEquals(aluno.getCpf(), alunoSaved.getCpf());
        assertEquals(aluno.getEndereco(), alunoSaved.getEndereco());

    }
}
