package co.idesoft.architetture.hexagonal.domain.valuables;

public record ClienteItem(
        Long id,
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        String descrizione) {

}
