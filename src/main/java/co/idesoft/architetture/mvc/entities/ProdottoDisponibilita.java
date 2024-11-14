package co.idesoft.architetture.mvc.entities;

import co.idesoft.architetture.mvc.controllers.dto.AggiornareProdottoDisponibilitaDto;
import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDisponibilitaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prodotti_disponibilita")
@Getter
@Setter
public class ProdottoDisponibilita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantita;

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

    public static ProdottoDisponibilita from(CreareProdottoDisponibilitaDto request, Long prodottoId) {

        ProdottoDisponibilita prodottoDisponibilita = new ProdottoDisponibilita();

        prodottoDisponibilita.setQuantita(request.quantita());
        prodottoDisponibilita.setProdottoId(prodottoId);
        prodottoDisponibilita.setWarehouseId(request.warehouseId());

        return prodottoDisponibilita;
    }

    public void aggiornaCon(AggiornareProdottoDisponibilitaDto request) {
        this.quantita = request.quantita();
        this.warehouseId = request.warehouseId();
    }
}
