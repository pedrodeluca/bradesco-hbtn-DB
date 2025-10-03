package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Telefone> telefones = new HashSet<>();

    public Aluno() {
    }

    // Mantemos compatibilidade com código que fornece List: convertemos para Set internamente
    public Aluno(String nome, List<Endereco> enderecos, List<Telefone> telefones) {
        this.nome = nome;
        if (enderecos != null) this.enderecos = new HashSet<>(enderecos);
        if (telefones != null) this.telefones = new HashSet<>(telefones);
    }

    public Aluno(long id, String nome, Set<Endereco> enderecos, Set<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

//    @Override
//    public String toString() {
//        return "id: " + this.id + " |XAXAD nome: " + this.nome + " | enderecos: " + this.enderecos.toString() + " | telefones: " + this.telefones.toString() + "\n";
//    }
}
