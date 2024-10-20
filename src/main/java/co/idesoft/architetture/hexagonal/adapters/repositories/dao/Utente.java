package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvaNuovoUsernameUtente;
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


    public static Utente from(SalvareUtente payload) {

        Utente utente = new Utente();

        utente.setUsername(payload.username());
        utente.setPassword(payload.password());
        utente.setUserStatusCode(payload.userStatusCode());

        return utente;
    }

    public void aggiornaCon(SalvaNuovoUsernameUtente payload) {
        this.username = payload.username();
    }

}
