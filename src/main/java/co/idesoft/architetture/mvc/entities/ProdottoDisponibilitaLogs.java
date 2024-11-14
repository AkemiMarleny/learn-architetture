package co.idesoft.architetture.mvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prodotti_disponibilita_logs")
@Getter
@Setter
public class ProdottoDisponibilitaLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantita;

    private Long prodottoDisponibilitaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodotto_id", insertable = false, updatable = false)
    private Prodotto prodotto;

    @Column(name = "prodotto_id", nullable = false)
    private Long prodottoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false)
    private Warehouse warehouse;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    public static ProdottoDisponibilitaLogs from(ProdottoDisponibilita prodottoDisponibilita) {

        ProdottoDisponibilitaLogs prodottoDisponibilitaLogs = new ProdottoDisponibilitaLogs();

        prodottoDisponibilitaLogs.setProdottoDisponibilitaId(prodottoDisponibilita.getId());
        prodottoDisponibilitaLogs.setQuantita(prodottoDisponibilita.getQuantita());
        prodottoDisponibilitaLogs.setProdottoId(prodottoDisponibilita.getProdottoId());
        prodottoDisponibilitaLogs.setWarehouseId(prodottoDisponibilita.getWarehouseId());

        return prodottoDisponibilitaLogs;
    }
}
