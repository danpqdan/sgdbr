package com.sgdb.sgdbr.Entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCurso;
    String nomeCurso;
    @ManyToMany
    @JoinTable(name = "curso_estudante", joinColumns = @JoinColumn(name = "estudante_id"),
    inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @JsonManagedReference
    Set<Aluno> aluno = new HashSet<>();

}
