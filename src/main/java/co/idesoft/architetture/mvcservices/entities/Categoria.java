package co.idesoft.architetture.mvcservices.entities;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCategoriaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCategoriaDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorie")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String nome;

    private String descrizione;

    @Column(length = 50)
    private String checksum;

    public static Categoria from(CreareCategoriaDto request) {
        Categoria categoria = new Categoria();

        categoria.setNome(request.nome());
        categoria.setDescrizione(request.descrizione());
        categoria.setChecksum(Sum.fromContent(request.nome().trim().toLowerCase()));

        return categoria;
    }

    public void aggiornaCon(AggiornareCategoriaDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
