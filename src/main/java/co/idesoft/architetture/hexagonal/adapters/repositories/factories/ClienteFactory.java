package co.idesoft.architetture.hexagonal.adapters.repositories.factories;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteFactory {
    public static ClienteDettaglio from(Cliente cliente) {
        return new ClienteDettaglio(cliente.getId(), cliente.getNome(), cliente.getDescrizione());
    }
}
