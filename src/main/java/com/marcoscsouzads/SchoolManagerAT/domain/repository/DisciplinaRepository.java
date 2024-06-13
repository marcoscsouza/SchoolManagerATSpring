package com.marcoscsouzads.SchoolManagerAT.domain.repository;

import com.marcoscsouzads.SchoolManagerAT.domain.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
