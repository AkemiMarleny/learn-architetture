package co.idesoft.architetture.mvcservices.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareMagazzinoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "magazzini")
@Getter
@Setter
public class Magazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazzinoId;

    private String nome;

    private String descrizione;

    @Column(length = 50)
    private String checksum; 

    public static Magazzino from(CreareMagazzinoDto request) {
        Magazzino magazzino = new Magazzino();

        magazzino.setNome(request.nome());
        magazzino.setDescrizione(request.descrizione());
        magazzino.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return magazzino;
    }

    public void aggiornaCon(AggiornareMagazzinoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
