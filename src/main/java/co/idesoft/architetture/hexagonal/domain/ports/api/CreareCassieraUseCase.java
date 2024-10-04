package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.hexagonal.domain.valuables.CreareCassiera;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import jakarta.validation.Valid;

public interface CreareCassieraUseCase {
    Long creareCassiera(@Valid CreareCassiera creareCassiera) throws ConflictException;
}
