package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.model.Professor;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.AlunoRepository;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.DisciplinaRepository;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

@SpringBootTest
public class ProfessorServiceTests {

    @Autowired
    private ProfessorService professorService;

    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    @MockBean
    private AlunoRepository alunoRepository;

    private Disciplina disciplina;
    private Aluno aluno;
    private Professor professor;

    @BeforeEach
    public void setUp() {
        disciplina = new Disciplina();
        disciplina.setNome("test");
        disciplina.setCodigo("codTest");

        aluno = new Aluno();
        aluno.setNome("test");
        aluno.setEndereco("sdsdf");
        aluno.setTelefone("123");
        aluno.setEmail("test@test.com");
        aluno.setCpf("123");

        professor = new Professor();
        professor.setDisciplina(disciplina);
        professor.setNota(7.0);

        Mockito.when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        Mockito.when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        Mockito.when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));


    }

    @Test
    public void saveProfessorTest() {
        Mockito.when(professorRepository.save(any(Professor.class))).thenReturn(professor);
        ResponseEntity<Professor> result = professorService.save(professor);
        Professor professorSaved = result.getBody();
        assertEquals(7.0, professorSaved.getNota());
    }

    @Test
    public void findAllAlunosApprovedTest(){
        Mockito.when(professorRepository.findByDisciplinaAndNotaGreaterThanEqual(any(Disciplina.class),anyDouble()))
                .thenReturn(Collections.singletonList(professor));
        ResponseEntity<List<Professor>> result = professorService.findApproved(1L);
        List<Professor> professorList = result.getBody();
        assertEquals(1, professorList.size());
        assertEquals(7.0, professorList.get(0).getNota());

    }

    @Test
    public void findAllAlunosApprovedNotFoundTest(){
        Mockito.when(professorRepository.findByDisciplinaAndNotaGreaterThanEqual(any(Disciplina.class),anyDouble()))
                .thenReturn(Collections.singletonList(professor));
        ResponseEntity<List<Professor>> result = professorService.findApproved(3L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());

    }

    @Test
    public void findAllAlunosDisapprovedTest(){
        Mockito.when(professorRepository.findByDisciplinaAndNotaLessThan(any(Disciplina.class),anyDouble()))
                .thenReturn(Collections.singletonList(professor));
        ResponseEntity<List<Professor>> result = professorService.findDisapproved(1L);
        List<Professor> professorList = result.getBody();
        assertEquals(1, professorList.size());
        assertEquals(7.0, professorList.get(0).getNota());

    }

    @Test
    public void findAllAlunosDisapprovedNotFoundTest(){
        Mockito.when(professorRepository.findByDisciplinaAndNotaLessThan(any(Disciplina.class),anyDouble()))
                .thenReturn(Collections.singletonList(professor));
        ResponseEntity<List<Professor>> result = professorService.findDisapproved(3L);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());

    }


}
