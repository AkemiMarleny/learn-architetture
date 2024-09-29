package co.idesoft.architetture.mvc.entities;

import co.idesoft.architetture.mvc.controllers.dto.AggiornareProdottoDto;
import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prodotti")
@Getter
@Setter
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodottoId;

    private String nome;

    private String descrizione;

    public static Prodotto from(CreareProdottoDto request) {
        Prodotto prodotto = new Prodotto();

        prodotto.setNome(request.nome());
        prodotto.setDescrizione(request.descrizione());

        return prodotto;
    }

    public void aggiornaCon(AggiornareProdottoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
