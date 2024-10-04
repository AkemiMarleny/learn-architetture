package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.hexagonal.domain.valuables.AggiornaCassiera;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;

public interface AggiornareCassieraUseCase {
    void aggiornaCassiera(@Valid AggiornaCassiera payload) throws ConflictException, RecordNotFoundException;
}
