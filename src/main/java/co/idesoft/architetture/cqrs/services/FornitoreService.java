package co.idesoft.architetture.cqrs.services;

import co.idesoft.architetture.cqrs.commands.AggiornareFornitoreCommand;
import co.idesoft.architetture.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.architetture.cqrs.entities.Fornitore;
import co.idesoft.architetture.cqrs.repositories.FornitoreRepository;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class FornitoreService {

    private final FornitoreRepository fornitoreRepository;

    public Long save(@Valid CreareFornitoreCommand command) throws ConflictException {
        Fornitore fornitore = Fornitore.from(command);

        Long fornitoriContatore = fornitoreRepository.countByChecksum(fornitore.getChecksum());

        if (fornitoriContatore > 0) {
            throw new ConflictException();
        }

        return fornitoreRepository.save(fornitore).getId();
    }

    public void update(Long fornitoreId, @Valid AggiornareFornitoreCommand command)
            throws RecordNotFoundException, ConflictException {
        // Fornitore fornitore = fornitoreRepository.findById(fornitoreId)
        // .orElseThrow(RecordNotFoundException::new);
        // fornitore.aggiornaCon(command);
        // fornitoreRepository.save(fornitore);

        Optional<Fornitore> fornitore = fornitoreRepository.findById(fornitoreId);

        if (fornitore.isPresent()) {
            Fornitore fornitoreModificare = fornitore.get();

            fornitoreModificare.aggiornaCon(command);

            Long fornitoriContatore = fornitoreRepository.countByChecksumAndIdNotIn(fornitoreModificare.getChecksum(),
                    Arrays.asList(fornitoreId));

            if (fornitoriContatore > 0) {
                throw new ConflictException();
            }

            fornitoreRepository.save(fornitoreModificare);
        } else {
            throw new RecordNotFoundException();
        }

    }

    public void cancella(Long fornitoreId) {
        fornitoreRepository.deleteById(fornitoreId);
    }
}
