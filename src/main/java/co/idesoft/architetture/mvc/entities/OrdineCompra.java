package co.idesoft.architetture.mvc.entities;

import co.idesoft.architetture.mvc.controllers.dto.CreareOrdineCompraProdottiDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ordini_compra")
@Getter
@Setter
public class OrdineCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodotto_id", insertable = false, updatable = false)
    private Prodotto prodotto;

    @Column(name = "prodotto_id", nullable = false)
    private Long prodottoId;

    private Long quantita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    public static OrdineCompra from(CreareOrdineCompraProdottiDto request) {

        OrdineCompra ordineCompra = new OrdineCompra();

        ordineCompra.setProdottoId(request.prodottoId());
        ordineCompra.setQuantita(request.quantita());
        ordineCompra.setCustomerId(request.customerId());

        return ordineCompra;
    }
}
