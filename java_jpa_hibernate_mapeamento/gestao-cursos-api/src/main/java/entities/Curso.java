package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private MaterialCurso materialCurso;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Professor professor;

    @OneToMany
    private List<Aluno> alunos;

    public Curso(String nome, MaterialCurso materialCurso, Professor professor, List<Aluno> alunos) {
        this.nome = nome;
        this.materialCurso = materialCurso;
        this.professor = professor;
        this.alunos = alunos;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MaterialCurso getMaterialCurso() {
        return materialCurso;
    }

    public void setMaterialCurso(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
