package co.idesoft.architetture.hexagonal.adapters.http.dto;

import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;

public record ClienteItemDto(
    Long id,
    String nome,
    String descrizione
) {

    public static ClienteItemDto from(ClienteItem clienteItem){
        return new ClienteItemDto(
            clienteItem.id(),
            clienteItem.nome(),
            clienteItem.descrizione()
        );
    }
    
}
