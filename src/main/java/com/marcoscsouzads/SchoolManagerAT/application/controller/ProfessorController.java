package com.marcoscsouzads.SchoolManagerAT.application.controller;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Professor;
import com.marcoscsouzads.SchoolManagerAT.domain.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessores() {
        return professorService.findAll();
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return professorService.save(professor);
    }

    @GetMapping("/aprovados/{id}")
    public ResponseEntity<List<Professor>> findByApproved(@PathVariable Long id) {
        if (professorService.findApproved(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return professorService.findApproved(id);
    }

    @GetMapping("/reprovados/{id}")
    public ResponseEntity<List<Professor>> findByDisapproved(@PathVariable Long id) {
        if (professorService.findDisapproved(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return professorService.findDisapproved(id);
    }


}
