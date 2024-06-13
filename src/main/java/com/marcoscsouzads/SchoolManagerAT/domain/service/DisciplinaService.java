package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.DisciplinaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public ResponseEntity<List<Disciplina>> findAll(){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    public ResponseEntity<Disciplina> save(Disciplina disciplina){
        var disciplinaModel = new Disciplina();
        BeanUtils.copyProperties(disciplina, disciplinaModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(disciplinaRepository.save(disciplinaModel));
    }

}

