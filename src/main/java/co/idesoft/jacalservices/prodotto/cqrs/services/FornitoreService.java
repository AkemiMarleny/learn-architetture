package co.idesoft.jacalservices.prodotto.cqrs.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.jacalservices.prodotto.cqrs.entities.Fornitore;
import co.idesoft.jacalservices.prodotto.cqrs.repositories.FornitoreRepository;
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
}
