package co.idesoft.architetture.cqrs.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.cqrs.commands.AggiornareFornitoreCommand;
import co.idesoft.architetture.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.architetture.cqrs.entities.Fornitore;
import co.idesoft.architetture.cqrs.repositories.FornitoreRepository;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class FornitoreService {

    private final FornitoreRepository fornitoreRepository;

    public Long creareFornitore(@Valid CreareFornitoreCommand command) {
        return fornitoreRepository.save(Fornitore.from(command)).getId();
    }

    public void update(Long fornitoreId, @Valid AggiornareFornitoreCommand command) throws RecordNotFoundException{
        Fornitore fornitore = fornitoreRepository.findById(fornitoreId)
            .orElseThrow(RecordNotFoundException::new);

            fornitore.aggiornaCon(command);

            fornitoreRepository.save(fornitore);
    }

    public void cancella(Long fornitoreId){
        fornitoreRepository.deleteById(fornitoreId);
    }
}
