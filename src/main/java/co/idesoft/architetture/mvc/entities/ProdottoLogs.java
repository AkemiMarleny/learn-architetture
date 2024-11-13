package co.idesoft.architetture.mvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prodotti_logs")
@Getter
@Setter
public class ProdottoLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long prodottoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unita_misura_id", insertable = false, updatable = false)
    private UnitaMisura unitaMisura;

    @Column(name = "unita_misura_id", nullable = false)
    private Long unitaMisuraId;

    private String descrizione;

    private Long stockTotale;

    @Column(length = 50)
    private String checksum;

    public static ProdottoLogs from(Prodotto prodotto) {

        ProdottoLogs prodottoLogs = new ProdottoLogs();

        prodottoLogs.setProdottoId(prodotto.getProdottoId());
        prodottoLogs.setNome(prodotto.getNome());
        prodottoLogs.setUnitaMisuraId(prodotto.getUnitaMisuraId());
        prodottoLogs.setDescrizione(prodotto.getDescrizione());
        prodottoLogs.setStockTotale(prodotto.getStockTotale());
        prodottoLogs.setChecksum(prodotto.getChecksum());

        return prodottoLogs;
    }
}
