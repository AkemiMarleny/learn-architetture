package co.idesoft.architetture.hexagonal.adapters.repositories.factories;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteFactory {
    public static ClienteDettaglio clienteDettaglioFrom(Cliente cliente) {
        return new ClienteDettaglio(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCognomePaterno(),
                cliente.getCognomeMaterno(),
                cliente.getDescrizione());
    }

    public static ClienteItem clienteItemFrom(Cliente cliente) {
        return new ClienteItem(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDescrizione());
    }
}
