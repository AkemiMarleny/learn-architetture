package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvaAggiornamentoCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clienti")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognomePaterno;
    private String cognomeMaterno;
    private LocalDate compleanno;
    private String descrizione;

    @Column(length = 50)
    private String checksum;

    private LocalDateTime dataEliminazione;

    public static Cliente from(SalvareCliente payload) {

        Cliente cliente = new Cliente();

        cliente.setNome(payload.nome());
        cliente.setCognomePaterno(payload.cognomePaterno());
        cliente.setCognomeMaterno(payload.cognomeMaterno());
        cliente.setCompleanno(payload.compleanno());
        cliente.setDescrizione(payload.descrizione());
        cliente.setChecksum(payload.checksum().get());

        return cliente;
    }

    public void aggiornaCon(SalvaAggiornamentoCliente payload) {

        this.nome = payload.nome();
        this.cognomePaterno = payload.cognomePaterno();
        this.cognomeMaterno = payload.cognomeMaterno();
        this.descrizione = payload.descrizione();
        this.checksum = payload.checksum().get();
    }

    public void cancella() {
        this.dataEliminazione = LocalDateTime.now();
    }
}
