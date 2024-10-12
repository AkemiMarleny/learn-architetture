package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

}
