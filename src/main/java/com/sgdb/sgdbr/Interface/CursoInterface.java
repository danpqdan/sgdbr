package com.sgdb.sgdbr.Interface;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgdb.sgdbr.Entidades.Curso;

@Repository
public interface CursoInterface extends JpaRepository<Curso, Long>{
    
    Optional<Curso> findBynomeCurso(String nomeCurso);
}
