package co.idesoft.architetture.hexagonal.adapters.repositories.dao;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clienti")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;

    public static Cliente from(SalvareCliente salvareCliente) {
        Cliente cliente = new Cliente();
        
        cliente.setNome(salvareCliente.nome());
        cliente.setDescrizione(salvareCliente.descrizione());
        
        return cliente;
    }
}
