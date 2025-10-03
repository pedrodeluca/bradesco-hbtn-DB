package records;

import entities.Telefone;

import java.util.Arrays;
import java.util.List;

public record ListaTelefone(List<Telefone> telefonesA1, List<Telefone> telefonesA2, List<Telefone> telefonesA3, List<Telefone> telefonesA4, List<Telefone> telefonesA5) {
    public static ListaTelefone getMontaListaTelefone() {
        List<Telefone> telefonesA1 = Arrays.asList(
                new Telefone("11999999999"),
                new Telefone("11888888888")
        );

        List<Telefone> telefonesA2 = Arrays.asList(
                new Telefone("11999999999"),
                new Telefone("11888888888")
        );

        List<Telefone> telefonesA3 = Arrays.asList(
                new Telefone("11999999999"),
                new Telefone("11888888888")
        );

        List<Telefone> telefonesA4 = Arrays.asList(
                new Telefone("11999999999"),
                new Telefone("11888888888")
        );

        List<Telefone> telefonesA5 = Arrays.asList(
                new Telefone("11999999999"),
                new Telefone("11888888888")
        );

        return new ListaTelefone(telefonesA1, telefonesA2, telefonesA3, telefonesA4, telefonesA5);
    }
}
