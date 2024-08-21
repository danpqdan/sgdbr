package com.sgdb.sgdbr;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgdb.sgdbr.Entidades.Aluno;
import com.sgdb.sgdbr.Entidades.Curso;
import com.sgdb.sgdbr.Interface.AlunoInterface;
import com.sgdb.sgdbr.Interface.CursoInterface;

@RestController
public class Controller {

    @Autowired
    AlunoInterface alunoInterface;
    @Autowired
    CursoInterface cursoInterface;

    public String adicionarAluno(AlunoDTO aluno) {
        Aluno aluno1 = new Aluno();
        aluno1.setNome(aluno.nome());
        alunoInterface.save(aluno1);
        return "Aluno salvo";
    }

    public String relacionarAlunoCurso(AlunoDTO aluno) {
        Curso curso1 = cursoInterface.findBynomeCurso(aluno.curso())
                .orElseThrow(() -> new RuntimeException("Curso not found"));
        Aluno aluno2 = alunoInterface.findByNome(aluno.nome())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        curso1.getAluno().add(aluno2);
        aluno2.getCurso().add(curso1);
        cursoInterface.save(curso1);
        alunoInterface.save(aluno2);
        return "Aluno salvo com curso";
    }

    public String novoCurso(CursoDTO curso) {
        Curso curso1 = new Curso();
        curso1.setNomeCurso(curso.nome());
        if (cursoInterface.findBynomeCurso(curso1.getNomeCurso()).equals(curso.nome())) {
            return "Curso ja existe";
        }
        cursoInterface.save(curso1);
        return "Curso salvo";
    }

    @PostMapping("/aluno/adicionar")
    public ResponseEntity adicionarAlunoNovo(@RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok().body(adicionarAluno(alunoDTO));
    }

    @PostMapping("/curso/adicionar")
    public ResponseEntity adicionarNovoCurso(@RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok().body(novoCurso(cursoDTO));
    }

    @PostMapping("/alunocurso")
    public ResponseEntity adicionarAlunoCurso(@RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok().body(relacionarAlunoCurso(alunoDTO));
    }

    @GetMapping("/lista/alunos")
    public ResponseEntity listaDeChamada(@RequestBody CursoDTO cursoDTO){
        var a = cursoInterface.findBynomeCurso(cursoDTO.nome());
        return ResponseEntity.ok().body(a);

    }

}
