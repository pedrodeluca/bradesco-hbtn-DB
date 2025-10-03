package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto update(Produto p) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, p.getId());

        if (produto != null) {
            produto.setNome(p.getNome());
            produto.setPreco(p.getPreco());
            produto.setQuantidade(p.getQuantidade());
            produto.setStatus(p.isStatus());

            em.merge(produto);

            System.out.println("Produto atualizado com sucesso !!!");

            findById(produto);
        } else {
            System.out.println("Produto não encontrado para atualização !!!");
        }

        em.getTransaction().commit();
        em.close();
        return produto;
    }

    public void delete(Produto p) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, p.getId());

        if (produto != null) {
            em.remove(produto);
            System.out.println("Produto removido com sucesso !!!");
            System.out.println(produto.toString());
        } else {
            System.out.println("Produto não encontrado para remoção !!!");
        }
        em.getTransaction().commit();
        em.close();
    }

    public Produto findById(Produto p) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, p.getId());
        em.getTransaction().commit();
        em.close();

        return produto;
    }

    public List<Produto> findAll() {

        List<Produto> produtos = new ArrayList<Produto>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        produtos = em.createQuery("from Produto p").getResultList();
        em.getTransaction().commit();
        em.close();

        return produtos;
    }
}