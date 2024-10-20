package co.idesoft.architetture.hexagonal.domain.valuables;

import java.time.LocalDate;

public record SalvareCliente(
        String nome,
        String cognomePaterno,
        String cognomeMaterno,
        LocalDate compleanno,
        String descrizione,
        ClienteChecksum checksum,
        Long utenteId) {

}
