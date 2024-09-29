package co.idesoft.jacalservices.prodotto.cqrs.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.cqrs.commands.AggiornareFornitoreCommand;
import co.idesoft.jacalservices.prodotto.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.jacalservices.prodotto.cqrs.entities.Fornitore;
import co.idesoft.jacalservices.prodotto.cqrs.repositories.FornitoreRepository;
import co.idesoft.jacalservices.prodotto.mvcservices.exceptions.RecordNotFoundException;
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
