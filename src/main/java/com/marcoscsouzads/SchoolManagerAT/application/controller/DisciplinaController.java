package com.marcoscsouzads.SchoolManagerAT.application.controller;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> getAllDisciplinas() {
        return disciplinaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaService.save(disciplina);
    }

}
