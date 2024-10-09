package co.idesoft.architetture.mvcservices.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareDipendenteDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareDipendenteDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dipendenteId;

    private String nome;

    private String descrizione;

    @Column(length = 50)
    private String checksum;

    public static Dipendente from(CreareDipendenteDto request) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(request.nome());
        dipendente.setDescrizione(request.descrizione());
        dipendente.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return dipendente;
    }

    public void aggiornaCon(AggiornareDipendenteDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
        this.checksum = Sum.fromContent(request.nome().trim().toLowerCase());
    }
}
