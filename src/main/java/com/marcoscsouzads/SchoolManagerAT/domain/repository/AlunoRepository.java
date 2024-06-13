package com.marcoscsouzads.SchoolManagerAT.domain.repository;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
