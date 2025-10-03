package records;

import entities.Endereco;

import java.util.Arrays;
import java.util.List;

public record ListaEndereco(List<Endereco> enderecosA1, List<Endereco> enderecosA2, List<Endereco> enderecosA3, List<Endereco> enderecosA4, List<Endereco> enderecosA5) {
    public static ListaEndereco getMontaListaEndereco() {
        List<Endereco> enderecosA1 = Arrays.asList(
                new Endereco("Rua A, numero 1"),
                new Endereco("Rua B, numero 2")
        );

        List<Endereco> enderecosA2 = Arrays.asList(
                new Endereco("Rua C, numero 1"),
                new Endereco("Rua D, numero 2")
        );

        List<Endereco> enderecosA3 = Arrays.asList(
                new Endereco("Rua E, numero 1"),
                new Endereco("Rua F, numero 2")
        );

        List<Endereco> enderecosA4 = Arrays.asList(
                new Endereco("Rua G, numero 1"),
                new Endereco("Rua H, numero 2")
        );

        List<Endereco> enderecosA5 = Arrays.asList(
                new Endereco("Rua I, numero 1"),
                new Endereco("Rua J, numero 2")
        );

        return new ListaEndereco(enderecosA1, enderecosA2, enderecosA3, enderecosA4, enderecosA5);
    }
}
