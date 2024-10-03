package co.idesoft.architetture.hexagonal.domain.valuables;

public record SalvaAggiornamentoCliente(
    Long id,
    String nome,
    String descrizione,
    ClienteChecksum checksum
) {}
