package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.model.Professor;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.DisciplinaRepository;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public ResponseEntity<List<Professor>> findAll(){
        List<Professor> professorList = professorRepository.findAll();
        return ResponseEntity.ok(professorList);
    }

    public ResponseEntity<Professor> save(Professor professor){
        var professorSaved = professorRepository.save(professor);
        BeanUtils.copyProperties(professor, professorSaved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorRepository.save(professorSaved));
    }

    public ResponseEntity<List<Professor>> findApproved(Long id){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Professor> approved = professorRepository.findByDisciplinaAndNotaGreaterThanEqual(disciplina.orElse(null), 7.0);
        if(approved.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(approved);


    }

    public ResponseEntity<List<Professor>> findDisapproved(Long id){
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        List<Professor> disapproved = professorRepository.findByDisciplinaAndNotaLessThan(disciplina.orElse(null), 7.0);
        if(disapproved.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(disapproved);

    }




}
