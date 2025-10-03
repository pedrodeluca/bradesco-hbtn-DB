package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transaçao");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transaçao");
        }
    }

    public Curso findById(Long id) {
        // TODO
        return null;
    }

    public List<Curso> findAll() {
        // TODO
        return null;
    }

    public void update(Curso curso) {
        // TODO
    }

    public void delete(Curso curso) {
        // TODO
    }
}
