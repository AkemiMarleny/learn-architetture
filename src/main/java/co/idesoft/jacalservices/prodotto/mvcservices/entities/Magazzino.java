package co.idesoft.jacalservices.prodotto.mvcservices.entities;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareMagazzinoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "magazzini")
@Getter
@Setter
public class Magazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazzinoId;

    private String nome;

    private String descrizione;

    public static Magazzino from(CreareMagazzinoDto request) {
        Magazzino magazzino = new Magazzino();

        magazzino.setNome(request.nome());
        magazzino.setDescrizione(request.descrizione());

        return magazzino;
    }

    public void aggiornaCon(AggiornareMagazzinoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
