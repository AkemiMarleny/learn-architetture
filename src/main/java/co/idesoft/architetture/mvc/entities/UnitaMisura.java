package co.idesoft.architetture.mvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unita_misura")
@Getter
@Setter
public class UnitaMisura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descrizione;

    private String checksum;
}
