package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import co.idesoft.architetture.hexagonal.domain.services.SalvaAggiornamentoCassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cassiere")
@Getter
@Setter
public class Cassiera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descrizione;

    @Column(length = 50)
    private String checksum;

    public static Cassiera from(SalvareCassiera payload) {
        Cassiera cassiera = new Cassiera();

        cassiera.setNome(payload.nome());
        cassiera.setDescrizione(payload.descrizione());
        cassiera.setChecksum(payload.checksum().get());

        return cassiera;
    }

    public void aggiornaCon(SalvaAggiornamentoCassiera payload) {

        this.nome = payload.nome();
        this.descrizione = payload.descrizione();
        this.checksum = payload.checksum().get();
    }

}