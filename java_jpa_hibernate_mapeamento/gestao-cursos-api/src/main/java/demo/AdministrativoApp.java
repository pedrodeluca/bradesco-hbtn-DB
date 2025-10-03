package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        Produto p2 = new Produto();
        p2.setNome("Notebook");
        p2.setPreco(1400.0);
        p2.setQuantidade(10);
        p2.setStatus(false);

        // 1) Criando um produto
        produtoModel.create(p1);
        produtoModel.create(p2);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        for (Produto prd : produtos) {
            System.out.println(prd.toString());
        }

        //3) Buscando produto por id
        p1.setId(1);
        Produto produtoById = produtoModel.findById(p1);
        if (produtoById != null) {
            System.out.println("Produto encontrado pelo id: " + produtoById.toString());
        } else {
            System.out.println("Produto não encontrado !!!");
        }

        //4) Atualizando um produto
        p1.setId(1);
        p1.setNome("Tablet");
        p1.setPreco(280.0);
        p1.setQuantidade(120);
        p1.setStatus(false);
        Produto produtoAtualizado = produtoModel.update(p1);
        System.out.println("Produto atualizado: " + produtoAtualizado.toString());

        //5) Deletando um produto
        p1.setId(2);
        produtoModel.delete(p1);

        //6) Listando novamente todos os produtos na base de dados
        produtos = produtoModel.findAll();
        for (Produto prd : produtos) {
            System.out.println(prd.toString());
        }

        acessaTabelaPessoa();
    }

    public static void acessaTabelaPessoa() {
        PessoaModel pessoaModel = new PessoaModel();

        Pessoa p1 = new Pessoa();
        p1.setNome("Pedro H Deluca");
        p1.setDataDeNascimento("30/07/1987");
        p1.setEmail("pedrodluk@email.com");
        p1.setCpf(12345678910L);
        p1.setIdade(38);

        Pessoa p2 = new Pessoa();
        p2.setNome("Vanessa L C Deluca");
        p2.setDataDeNascimento("30/07/1984");
        p2.setEmail("vanessa@email.com");
        p2.setCpf(12345678910L);
        p2.setIdade(40);

        // 1) Criando um produto
        pessoaModel.create(p1);
        pessoaModel.create(p2);

        //2) Buscando todos os produtos na base de dados
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + pessoas.size());

        for (Pessoa psa : pessoas) {
            System.out.println(psa.toString());
        }

        //3) Buscando produto por id
        p1.setId(1);
        Pessoa pessoaById = pessoaModel.findById(p1);
        if (pessoaById != null) {
            System.out.println("Pessoa encontrada pelo id: " + pessoaById.toString());
        } else {
            System.out.println("Pessoa não encontrada !!!");
        }

        //4) Atualizando um produto
        p1.setId(1);
        p2.setNome("Vanessa Larissa Cardoso Deluca");
        p2.setDataDeNascimento("30/07/1984");
        p2.setEmail("vanessa@email.com");
        p2.setCpf(12345678910L);
        p2.setIdade(40);
        Pessoa pessoaAtualizada = pessoaModel.update(p1);
        System.out.println("Produto atualizado: " + pessoaAtualizada.toString());

        //5) Deletando um produto
        p1.setId(2);
        pessoaModel.delete(p1);

        //6) Listando novamente todos os produtos na base de dados
        pessoas = pessoaModel.findAll();
        for (Pessoa psa : pessoas) {
            System.out.println(psa.toString());
        }
    }
}