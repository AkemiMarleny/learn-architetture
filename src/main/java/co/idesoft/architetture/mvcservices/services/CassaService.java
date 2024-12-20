package co.idesoft.architetture.mvcservices.services;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCassaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCassaDto;
import co.idesoft.architetture.mvcservices.entities.Cassa;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.CassaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class CassaService {

    private final CassaRepository cassaRepository;

    public Long save(@Valid CreareCassaDto payload) throws ConflictException {
        Cassa cassa = Cassa.from(payload);

        Long casseContatore = cassaRepository.countByChecksum(cassa.getChecksum());

        if (casseContatore > 0) {
            throw new ConflictException();
        }

        return cassaRepository.save(cassa).getCassaId();
    }

    public Page<Cassa> findAll(Pageable pageable, String query) {
        return cassaRepository.findByNomeContaining(pageable, query);
    }

    public Cassa findDettaglio(Long cassaId) throws RecordNotFoundException {
        return cassaRepository.findById(cassaId).orElseThrow(RecordNotFoundException::new);
    }

    public void update(Long cassaId, @Valid AggiornareCassaDto payload)
            throws RecordNotFoundException, ConflictException {
        // Cassa cassa = cassaRepository.findById(cassaId)
        // .orElseThrow(RecordNotFoundException::new);
        // cassa.aggiornaCon(payload);
        // cassaRepository.save(cassa);

        Optional<Cassa> casse = cassaRepository.findById(cassaId);

        if (casse.isPresent()) {
            Cassa cassaModificare = casse.get();

            cassaModificare.aggiornaCon(payload);

            Long casseContantore = cassaRepository.countByChecksumAndCassaIdNotIn(cassaModificare.getChecksum(),
                    Arrays.asList(cassaId));

            if (casseContantore > 0) {
                throw new ConflictException();
            }

            cassaRepository.save(cassaModificare);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void cancella(Long cassaId) {
        cassaRepository.deleteById(cassaId);
    }
}
