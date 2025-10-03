package demo;

import java.util.ArrayList;
import java.util.List;
import entities.Aluno;
import entities.Curso;
import entities.MaterialCurso;
import entities.Professor;
import models.AlunoModel;
import models.CursoModel;
import records.ListaEndereco;
import records.ListaTelefone;


public class GestaoCursosMain {

    public static void main(String[] args) {

        System.out.println("Gestao de Cursos - JPA");

        //Tratando cadastro de alunos
        ListaEndereco listaEndereco = ListaEndereco.getMontaListaEndereco();
        ListaTelefone listaTelefones = ListaTelefone.getMontaListaTelefone();

        List<Aluno> alunos = cadastrarEPersistirAlunos(listaEndereco, listaTelefones);

        //Tratando cadastro de professores
        List<MaterialCurso> listaMaterialCurso = montarMaterialCurso();
        List<Curso> cursos = montarListaCursos(listaMaterialCurso);
        List<Professor> listaProfessores = montarListaProfessores();

        //Relaciona alunos aos cursos
        Professor professor1 =
                new Professor(listaProfessores.get(0).getNome(), List.of(
                        new Curso(cursos.get(0).getNome(), listaMaterialCurso.get(0), listaProfessores.get(0), List.of(alunos.get(0), alunos.get(1), alunos.get(2)))));
        Professor professor2 =
                new Professor(listaProfessores.get(1).getNome(), List.of(
                        new Curso(cursos.get(1).getNome(), listaMaterialCurso.get(1), listaProfessores.get(1) ,List.of(alunos.get(3), alunos.get(4)))));

        //Persiste nas tabelas de Curso, Professor e MaterialCurso
        CursoModel cursoModel = new CursoModel();
        cursoModel.create(professor1.getCursos().get(0));
        cursoModel.create(professor2.getCursos().get(0));

        //Inicia chamada ao banco de dados
        gerirAlunos();

    }

    private static List<Aluno> cadastrarEPersistirAlunos(ListaEndereco listaEndereco, ListaTelefone listaTelefones) {
        List<Aluno> alunos = new ArrayList<>();

        Aluno a1 = new Aluno("Joao Paulo", listaEndereco.enderecosA1(), listaTelefones.telefonesA1());
        Aluno a2 = new Aluno("Maria Silva", listaEndereco.enderecosA2(), listaTelefones.telefonesA2());
        Aluno a3 = new Aluno("Pedro Santos", listaEndereco.enderecosA3(), listaTelefones.telefonesA3());
        Aluno a4 = new Aluno("Ana Costa", listaEndereco.enderecosA4(), listaTelefones.telefonesA4());
        Aluno a5 = new Aluno("Lucas Pereira", listaEndereco.enderecosA5(), listaTelefones.telefonesA5());

        //Relacionamento com as chaves estrangeiras
        a1.getEnderecos().forEach(end -> end.setAluno(a1));
        a1.getTelefones().forEach(tel -> tel.setAluno(a1));
        a2.getEnderecos().forEach(end -> end.setAluno(a2));
        a2.getTelefones().forEach(tel -> tel.setAluno(a2));
        a3.getEnderecos().forEach(end -> end.setAluno(a3));
        a3.getTelefones().forEach(tel -> tel.setAluno(a3));
        a4.getEnderecos().forEach(end -> end.setAluno(a4));
        a4.getTelefones().forEach(tel -> tel.setAluno(a4));
        a5.getEnderecos().forEach(end -> end.setAluno(a5));
        a5.getTelefones().forEach(tel -> tel.setAluno(a5));

        //Cria AlunoModel para persistir os alunos
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.create(a1);
        alunoModel.create(a2);
        alunoModel.create(a3);
        alunoModel.create(a4);
        alunoModel.create(a5);

        alunos.add(a1);
        alunos.add(a2);
        alunos.add(a3);
        alunos.add(a4);
        alunos.add(a5);
        return alunos;
    }

    private static List<MaterialCurso> montarMaterialCurso() {
        List<MaterialCurso> listaMaterialCurso = new ArrayList<>();

        MaterialCurso materialCurso1 = new MaterialCurso("Material de Java");
        MaterialCurso materialCurso2 = new MaterialCurso("Material de Python");
        MaterialCurso materialCurso3 = new MaterialCurso("Material de JavaScript");
        MaterialCurso materialCurso4 = new MaterialCurso("Material de C#");
        MaterialCurso materialCurso5 = new MaterialCurso("Material de PHP");

        listaMaterialCurso.add(materialCurso1);
        listaMaterialCurso.add(materialCurso2);
        listaMaterialCurso.add(materialCurso3);
        listaMaterialCurso.add(materialCurso4);
        listaMaterialCurso.add(materialCurso5);

        return listaMaterialCurso;
    }

    private static List<Professor> montarListaProfessores() {
        List<Professor> listaProfessores = new ArrayList<>();

        Professor p1 = new Professor("Prof. Carlos Alberto", null);
        Professor p2 = new Professor("Prof. Fernanda Lima", null);
        Professor p3 = new Professor("Prof. Marcos Vinicius", null);
        Professor p4 = new Professor("Prof. Juliana Souza", null);
        Professor p5 = new Professor("Prof. Ricardo Gomes", null);

        listaProfessores.add(p1);
        listaProfessores.add(p2);
        listaProfessores.add(p3);
        listaProfessores.add(p4);
        listaProfessores.add(p5);

        return listaProfessores;
    }

    private static List<Curso> montarListaCursos(List<MaterialCurso> listaMaterialCurso) {
        List<Curso> cursos = new ArrayList<>();

        Curso curso1 = new Curso("Java", new MaterialCurso("Curso de Java"), null, null);
        Curso curso2 = new Curso("Python", new MaterialCurso("Curso de Python"), null, null);

        cursos.add(curso1);
        cursos.add(curso2);

        return cursos;
    }

    private static void gerirAlunos() {
        // Pesquisa todos os alunos cadastrados
        AlunoModel alunoModel = new AlunoModel();
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Lista de Alunos Cadastrados:");
        System.out.println(alunos.toString());

        // Altera um aluno cadastrado
        Aluno alunoParaAlterar = alunos.get(0);
        System.out.println("Aluno antes da alteraçao: " + alunoParaAlterar);
        alunoParaAlterar.setNome("Joao Paulo da Silva");
        alunoModel.update(alunoParaAlterar);
        System.out.println("Aluno após a alteraçao: " + alunoParaAlterar);

        // consultar Aluno pelo id
        Aluno alunoPeloId = alunoModel.findById(2L);
        System.out.println("Aluno pesquisado pelo id: " + alunoPeloId);

        // removendo aluno
        System.out.println("Excluindo o registro pesquisado");
        alunoModel.delete(alunos.get(0));


    }
}