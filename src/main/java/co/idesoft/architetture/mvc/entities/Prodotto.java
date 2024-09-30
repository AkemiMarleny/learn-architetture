package co.idesoft.architetture.mvc.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvc.controllers.dto.AggiornareProdottoDto;
import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDto;
import jakarta.persistence.Column;
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

    @Column(length = 50)
    private String checksum;

    public static Prodotto from(CreareProdottoDto request) {
        Prodotto prodotto = new Prodotto();

        prodotto.setNome(request.nome());
        prodotto.setDescrizione(request.descrizione());
        prodotto.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return prodotto;
    }

    public void aggiornaCon(AggiornareProdottoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
