package co.idesoft.jacalservices.prodotto.mvcservices.entities;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCassaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "casse")
@Getter
@Setter
public class Cassa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cassaId;

    private String nome;

    private String descrizione;

    public static Cassa from(CreareCassaDto request) {
        Cassa cassa = new Cassa();

        cassa.setNome(request.nome());
        cassa.setDescrizione(request.descrizione());

        return cassa;
    }

}
