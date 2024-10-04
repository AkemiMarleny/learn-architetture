package co.idesoft.architetture.hexagonal.domain.valuables;

public record SalvareCassiera(
        String nome,
        String descrizione,
        CassieraChecksum checksum) {

}
