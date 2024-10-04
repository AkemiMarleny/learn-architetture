package co.idesoft.architetture.hexagonal.domain.services;

import co.idesoft.architetture.hexagonal.domain.valuables.CassieraChecksum;

public record SalvaAggiornamentoCassiera(
        Long id,
        String nome,
        String descrizione,
        CassieraChecksum checksum) {

}
