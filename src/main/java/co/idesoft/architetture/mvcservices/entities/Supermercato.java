package co.idesoft.architetture.mvcservices.entities;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareSupermercatoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareSupermercatoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supermercati")
@Getter
@Setter
public class Supermercato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supermercatoId;

    private String nome;

    private String descrizione;

    public static Supermercato from(CreareSupermercatoDto request) {
        Supermercato supermercato = new Supermercato();
        supermercato.setNome(request.nome());
        supermercato.setDescrizione(request.descrizione());
        return supermercato;
    }

    public void aggiornaCon(AggiornareSupermercatoDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }

}
