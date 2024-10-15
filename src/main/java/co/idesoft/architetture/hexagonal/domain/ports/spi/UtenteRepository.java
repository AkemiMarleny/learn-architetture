package co.idesoft.architetture.hexagonal.domain.ports.spi;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;

public interface UtenteRepository {

    Long save(SalvareUtente payload);
}
