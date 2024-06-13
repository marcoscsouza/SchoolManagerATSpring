package com.marcoscsouzads.SchoolManagerAT.domain.service;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import com.marcoscsouzads.SchoolManagerAT.domain.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public ResponseEntity<List<Aluno>> findAll(){
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseEntity.ok(alunos);
    }

    public ResponseEntity<Aluno> save(Aluno aluno) {
        var alunoModel = new Aluno();
        BeanUtils.copyProperties(aluno, alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoRepository.save(alunoModel));
    }
}
