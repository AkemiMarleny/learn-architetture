package co.idesoft.architetture.hexagonal.domain.valuables;

public record SalvareCliente(
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        String descrizione,
        ClienteChecksum checksum) {

}
