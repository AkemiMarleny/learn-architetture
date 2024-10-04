package co.idesoft.architetture.hexagonal.domain.ports.api;

import java.util.Optional;

import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;

public interface FindCassieraByIdUseCase {
    Optional<CassieraDettaglio> findCassieraById(Long cassieraId);
}
