package co.idesoft.architetture.hexagonal.adapters.http.dto;

public record AggiornareClienteDto(
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        String descrizione) {

}
