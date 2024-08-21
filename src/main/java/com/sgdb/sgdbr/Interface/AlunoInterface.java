package com.sgdb.sgdbr.Interface;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgdb.sgdbr.Entidades.Aluno;

@Repository
public interface AlunoInterface extends JpaRepository<Aluno, Integer>{

    Optional<Aluno> findByNome(String nome);
    
}
