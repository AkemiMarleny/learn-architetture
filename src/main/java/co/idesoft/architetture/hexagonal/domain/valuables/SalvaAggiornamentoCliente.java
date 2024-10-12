package co.idesoft.architetture.hexagonal.domain.valuables;

public record SalvaAggiornamentoCliente(
        Long id,
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        String descrizione,
        ClienteChecksum checksum) {
}
