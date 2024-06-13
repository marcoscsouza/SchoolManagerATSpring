package com.marcoscsouzads.SchoolManagerAT.application.controller;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.AlunoRepository;
import com.marcoscsouzads.SchoolManagerAT.domain.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        return alunoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }
}
