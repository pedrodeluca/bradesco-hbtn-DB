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

    private String nomeCompleto;

    private String matricula;

    private String nascimento;

    private String email;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Telefone> telefones = new HashSet<>();

    public Aluno() {
    }

    public Aluno(long id, String nome, Set<Endereco> enderecos, Set<Telefone> telefones) {
        this.id = id;
        this.nomeCompleto = nome;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Aluno(long id, String nomeCompleto, String matricula, String nascimento, String email) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
    }

    // Mantemos compatibilidade com código que fornece List: convertemos para Set internamente
    public Aluno(String nome, List<Endereco> enderecos, List<Telefone> telefones) {
        this.nomeCompleto = nome;
        if (enderecos != null) this.enderecos = new HashSet<>(enderecos);
        if (telefones != null) this.telefones = new HashSet<>(telefones);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
