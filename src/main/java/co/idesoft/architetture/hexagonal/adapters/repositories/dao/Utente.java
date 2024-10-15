package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 15)
    private String userStatusCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteId", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "clienteId", nullable = false)
    private Long clienteId;

    public static Utente from(SalvareUtente payload) {

        Utente utente = new Utente();

        utente.setUsername(payload.username());
        utente.setPassword(payload.password());
        utente.setUserStatusCode(payload.userStatusCode());
        utente.setClienteId(payload.clienteId());

        return utente;
    }

}
