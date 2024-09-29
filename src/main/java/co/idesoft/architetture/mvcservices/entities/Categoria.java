package co.idesoft.architetture.mvcservices.entities;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCategoriaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCategoriaDto;
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

    public static Categoria from(CreareCategoriaDto request) {
        Categoria categoria = new Categoria();

        categoria.setNome(request.nome());
        categoria.setDescrizione(request.descrizione());

        return categoria;
    }

    public void aggiornaCon(AggiornareCategoriaDto request) {
        this.nome = request.nome();
        this.descrizione = request.descrizione();
    }
}
