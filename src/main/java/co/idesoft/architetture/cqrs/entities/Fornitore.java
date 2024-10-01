package co.idesoft.architetture.cqrs.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.cqrs.commands.AggiornareFornitoreCommand;
import co.idesoft.architetture.cqrs.commands.CreareFornitoreCommand;
import jakarta.persistence.Column;
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

    @Column(length = 50)
    private String checksum;

    public static Fornitore from(CreareFornitoreCommand command) {
        Fornitore fornitore = new Fornitore();

        fornitore.setNome(command.nome());
        fornitore.setDescrizione(command.descrizione());
        fornitore.setChecksum(Sum.fromContent(command.nome().trim().toLowerCase()));

        return fornitore;
    }

    public void aggiornaCon(AggiornareFornitoreCommand command){
        this.nome = command.nome();
        this.descrizione = command.descrizione();
    }
}
