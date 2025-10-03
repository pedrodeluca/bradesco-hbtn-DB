package models;

import entities.Aluno;
import entities.Endereco;
import entities.Telefone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transaçao");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transaçao");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Aluno aluno = em.find(Aluno.class, id);
        em.getTransaction().commit();
        em.close();
        return aluno;
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        // Carrega apenas os Alunos (sem join-fetch)
        List<Aluno> alunos = em.createQuery(
                "select a from Aluno a", Aluno.class)
            .getResultList();

        if (!alunos.isEmpty()) {
            // Carrega endereços para os alunos retornados
            List<Endereco> enderecos = em.createQuery(
                    "select e from Endereco e where e.aluno in :alunos", Endereco.class)
                .setParameter("alunos", alunos)
                .getResultList();

            for (Endereco e : enderecos) {
                if (e.getAluno() != null) {
                    e.getAluno().getEnderecos().add(e);
                }
            }

            // Carrega telefones para os alunos retornados
            List<Telefone> telefones = em.createQuery(
                    "select t from Telefone t where t.aluno in :alunos", Telefone.class)
                .setParameter("alunos", alunos)
                .getResultList();

            for (Telefone t : telefones) {
                if (t.getAluno() != null) {
                    t.getAluno().getTelefones().add(t);
                }
            }
        }

        em.getTransaction().commit();
        em.close();

        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        System.out.println("excluindo registro: " + aluno);
        em.getTransaction().begin();
        // Se a entidade passada estiver detached, precisamos reatachar
        if (aluno.getId() != 0) {
            Aluno managed = em.find(Aluno.class, aluno.getId());
            if (managed != null) {
                em.remove(managed);
            } else {
                // fallback: merge e remove o resultado (reattacha)
                Aluno merged = em.merge(aluno);
                em.remove(merged);
            }
        } else {
            // sem id válido, tenta reatachar via merge
            Aluno merged = em.merge(aluno);
            em.remove(merged);
        }
        em.getTransaction().commit();
        em.close();
    }
}