package co.idesoft.architetture.hexagonal.domain.valuables;

public record SalvareUtente(
        String username,
        String password,
        String userStatusCode,
        Long clienteId
) {
}
