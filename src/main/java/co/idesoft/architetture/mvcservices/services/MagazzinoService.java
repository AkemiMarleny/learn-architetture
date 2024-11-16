package co.idesoft.architetture.mvcservices.services;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareMagazzinoDto;
import co.idesoft.architetture.mvcservices.entities.Magazzino;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.MagazzinoRepository;
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
public class MagazzinoService {

    private final MagazzinoRepository magazzinoRepository;

    public Page<Magazzino> findAll(Pageable pageable, String query) {
        return magazzinoRepository.findByNomeContaining(pageable, query);
    }

    public Long save(@Valid CreareMagazzinoDto payload) throws ConflictException {
        Magazzino magazzino = Magazzino.from(payload);

        Long magazziniContatore = magazzinoRepository.countByChecksum(magazzino.getChecksum());

        if (magazziniContatore > 0) {
            throw new ConflictException();
        }

        return magazzinoRepository.save(magazzino).getMagazzinoId();
    }

    public Optional<Magazzino> findDettaglio(Long magazzinoId) {
        return magazzinoRepository.findById(magazzinoId);
    }

    public void update(Long magazzinoId, @Valid AggiornareMagazzinoDto payload)
            throws RecordNotFoundException, ConflictException {

        // Magazzino magazzino = magazzinoRepository.findById(magazzinoId)
        // .orElseThrow(RecordNotFoundException::new);
        // magazzino.aggiornaCon(payload);
        // magazzinoRepository.save(magazzino);

        Optional<Magazzino> magazzini = magazzinoRepository.findById(magazzinoId);

        if (magazzini.isPresent()) {
            Magazzino magazzinoModificare = magazzini.get();

            magazzinoModificare.aggiornaCon(payload);

            Long magazziniContatore = magazzinoRepository
                    .countByChecksumAndMagazzinoIdNotIn(magazzinoModificare.getChecksum(), Arrays.asList(magazzinoId));

            if (magazziniContatore > 0) {
                throw new ConflictException();
            }

            magazzinoRepository.save(magazzinoModificare);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void cancella(Long magazzinoId) {
        magazzinoRepository.deleteById(magazzinoId);
    }
}
