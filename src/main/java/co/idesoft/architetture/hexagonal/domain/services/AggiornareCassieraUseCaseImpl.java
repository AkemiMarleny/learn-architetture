package co.idesoft.architetture.hexagonal.domain.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.hexagonal.domain.ports.api.AggiornareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.AggiornaCassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraChecksum;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AggiornareCassieraUseCaseImpl implements AggiornareCassieraUseCase {

    private final CassieraRepository cassieraRepository;

    @Override
    public void aggiornaCassiera(@Valid AggiornaCassiera payload) throws ConflictException, RecordNotFoundException {
        CassieraChecksum cassieraChecksum = new CassieraChecksum(payload.nome());

        Long cassiereContatore = cassieraRepository.countByChecksumAndIdNotIn(cassieraChecksum.get(),
                Arrays.asList(payload.id()));

        if (cassiereContatore > 0) {
            throw new ConflictException();
        }

        cassieraRepository.update(new SalvaAggiornamentoCassiera(
                payload.id(),
                payload.nome(),
                payload.descrizione(),
                cassieraChecksum));
    }

}
