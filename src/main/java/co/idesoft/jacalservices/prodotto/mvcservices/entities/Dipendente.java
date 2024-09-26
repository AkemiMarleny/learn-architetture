package co.idesoft.jacalservices.prodotto.mvcservices.entities;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareDipendenteDto;
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

    public static Dipendente from(CreareDipendenteDto request) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(request.nome());
        dipendente.setDescrizione(request.descrizione());
        return dipendente;
    }
}
