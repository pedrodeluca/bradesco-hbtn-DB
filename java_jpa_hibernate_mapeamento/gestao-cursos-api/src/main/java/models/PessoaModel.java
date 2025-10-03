package models;

import entities.Pessoa;
import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa = em.find(Pessoa.class, p.getId());

        if (pessoa != null) {
            pessoa.setNome(p.getNome());
            pessoa.setCpf(p.getCpf());
            pessoa.setEmail(p.getEmail());
            pessoa.setIdade(p.getIdade());
            pessoa.setDataDeNascimento(p.getDataDeNascimento());

            em.merge(pessoa);

            System.out.println("Pessoa atualizado com sucesso !!!");

            findById(pessoa);
        } else {
            System.out.println("Pessoa não encontrada para atualização !!!");
        }

        em.getTransaction().commit();
        em.close();

        return pessoa;
    }

    public void delete(Pessoa p) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa = em.find(Pessoa.class, p.getId());

        if (pessoa != null) {
            em.remove(pessoa);
            System.out.println("Pessoa removida com sucesso !!!");
            System.out.println(pessoa.toString());
        } else {
            System.out.println("Pessoa não encontrada para remoção !!!");
        }
        em.getTransaction().commit();
        em.close();
    }

    public Pessoa findById(Pessoa p) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa = em.find(Pessoa.class, p.getId());
        em.getTransaction().commit();
        em.close();

        return pessoa;
    }

    public List<Pessoa> findAll() {

        List<Pessoa> pessoa = new ArrayList<Pessoa>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        pessoa = em.createQuery("from Pessoa p").getResultList();
        em.getTransaction().commit();
        em.close();

        return pessoa;
    }
}