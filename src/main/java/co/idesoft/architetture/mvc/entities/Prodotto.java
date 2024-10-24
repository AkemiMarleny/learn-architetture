package co.idesoft.architetture.mvc.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvc.controllers.dto.AggiornareProdottoDto;
import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "prodotti")
@Getter
@Setter
@SoftDelete(columnName = "dataEliminazione")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodottoId;

    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unita_misura_id", insertable = false, updatable = false)
    private UnitaMisura unitaMisura;

    @Column(name = "unita_misura_id", nullable = false)
    private Long unitaMisuraId;

    private String descrizione;

    @Column(length = 50)
    private String checksum;

    public static Prodotto from(CreareProdottoDto request) {
        Prodotto prodotto = new Prodotto();

        prodotto.setNome(request.nome());
        prodotto.setUnitaMisuraId(request.unitaMisuraId());
        prodotto.setDescrizione(request.descrizione());
        prodotto.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return prodotto;
    }

    public void aggiornaCon(AggiornareProdottoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
        this.checksum = Sum.fromContent(request.nome().trim().toLowerCase());
    }
}
