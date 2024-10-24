package co.idesoft.architetture.mvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prodotto_disponibilita")
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
}
