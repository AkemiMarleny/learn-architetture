package co.idesoft.architetture.hexagonal.domain.valuables;

public record ClienteDettaglio(
        Long id,
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        String descrizione) {
}
