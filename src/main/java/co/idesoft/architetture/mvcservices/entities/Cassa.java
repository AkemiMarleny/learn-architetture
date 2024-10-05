package co.idesoft.architetture.mvcservices.entities;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCassaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCassaDto;
import jakarta.persistence.Column;
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
@SoftDelete(strategy = SoftDeleteType.DELETED)
public class Cassa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cassaId;

    private String nome;

    private String descrizione;

    @Column(length = 50)
    private String checksum;

    public static Cassa from(CreareCassaDto request) {
        Cassa cassa = new Cassa();

        cassa.setNome(request.nome());
        cassa.setDescrizione(request.descrizione());
        cassa.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return cassa;
    }

    public void aggiornaCon(AggiornareCassaDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }

}
