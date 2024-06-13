package com.marcoscsouzads.SchoolManagerAT.domain.repository;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import com.marcoscsouzads.SchoolManagerAT.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByDisciplinaAndNotaGreaterThanEqual(Disciplina disciplina, Double nota);
    List<Professor> findByDisciplinaAndNotaLessThan(Disciplina disciplina, Double nota);
}
