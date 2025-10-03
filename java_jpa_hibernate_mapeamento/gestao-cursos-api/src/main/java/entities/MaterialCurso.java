package entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "material_curso")
public class MaterialCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

//    @OneToMany
//    private List<Curso> cursos;

    public MaterialCurso(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    public List<Curso> getCursos() {
//        return cursos;
//    }
//
//    public void setCursos(List<Curso> cursos) {
//        this.cursos = cursos;
//    }
}
