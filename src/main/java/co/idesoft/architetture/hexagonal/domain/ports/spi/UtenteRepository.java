package co.idesoft.architetture.hexagonal.domain.ports.spi;

import co.idesoft.architetture.hexagonal.domain.valuables.SalvaNuovoUsernameUtente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;

import java.util.List;

public interface UtenteRepository {

    Long save(SalvareUtente payload);

    void update(SalvaNuovoUsernameUtente payload) throws RecordNotFoundException;

    Long countByUsername(String username);

    Long countByUsernameAndIdNotIn(String username, List<Long> utenteIds);
}
