package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.DisciplinaRepository;
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
public class DisciplinaServiceTests {

    @Autowired
    private DisciplinaService disciplinaService;

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    @Test
    public void findAllDisciplinasTest() {
        Disciplina testDisciplina = new Disciplina();
        testDisciplina.setNome("Test");
        testDisciplina.setCodigo("codigoTest");

        Mockito.when(disciplinaRepository.findAll()).thenReturn(Collections.singletonList(testDisciplina));
        ResponseEntity<List<Disciplina>> result = disciplinaService.findAll();
        List<Disciplina> disciplinas = result.getBody();

        if (!disciplinas.isEmpty()) {
            for (Disciplina disciplina : disciplinas) {
                assertEquals(testDisciplina.getNome(), disciplina.getNome());
                assertEquals(testDisciplina.getCodigo(), disciplina.getCodigo());
            }
        }
    }

    @Test
    public void saveDisciplinaTest() {

        Disciplina testDisciplina = new Disciplina();
        testDisciplina.setNome("Test");
        testDisciplina.setCodigo("codigoTest");
        Mockito.when(disciplinaRepository.save(testDisciplina)).thenReturn(testDisciplina);
        ResponseEntity<Disciplina> result = disciplinaService.save(testDisciplina);
        Disciplina disciplina = result.getBody();
        assertEquals(testDisciplina.getNome(), disciplina.getNome());
        assertEquals(testDisciplina.getCodigo(), disciplina.getCodigo());

    }
}
