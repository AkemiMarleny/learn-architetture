package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import java.time.LocalDateTime;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvaAggiornamentoCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clienti")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;

    @Column(length = 50)
    private String checksum;

    private LocalDateTime dataEliminazione;

    public static Cliente from(SalvareCliente payload) {
        Cliente cliente = new Cliente();

        cliente.setNome(payload.nome());
        cliente.setDescrizione(payload.descrizione());
        cliente.setChecksum(payload.checksum().get());

        return cliente;
    }

    public void aggiornaCon(SalvaAggiornamentoCliente payload) {
        this.nome = payload.nome();
        this.descrizione = payload.descrizione();
        this.checksum = payload.checksum().get();
    }

    public void cancella() {
        this.dataEliminazione = LocalDateTime.now();
    }
}
