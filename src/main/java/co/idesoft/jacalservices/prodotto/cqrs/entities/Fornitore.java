package co.idesoft.jacalservices.prodotto.cqrs.entities;

import co.idesoft.jacalservices.prodotto.cqrs.commands.CreareFornitoreCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fornitori")
@Getter
@Setter
public class Fornitore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;

    public static Fornitore from(CreareFornitoreCommand command) {
        Fornitore fornitore = new Fornitore();

        fornitore.setNome(command.nome());
        fornitore.setDescrizione(command.descrizione());

        return fornitore;
    }
}
