package co.idesoft.architetture.mvcservices.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareMagazzinoDto;
import co.idesoft.architetture.mvcservices.entities.Magazzino;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.MagazzinoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class MagazzinoService {

    private final MagazzinoRepository magazzinoRepository;

    public Page<Magazzino> findAll(Pageable pageable) {
        return magazzinoRepository.findAll(pageable);
    }

    public Long save(@Valid CreareMagazzinoDto payload) {
        return magazzinoRepository.save(Magazzino.from(payload)).getMagazzinoId();
    }

    public Optional<Magazzino> findDettaglio(Long magazzinoId) {
        return magazzinoRepository.findById(magazzinoId);
    }

    public void update(Long magazzinoId, @Valid AggiornareMagazzinoDto payload) throws RecordNotFoundException {
        Magazzino magazzino = magazzinoRepository.findById(magazzinoId)
                .orElseThrow(RecordNotFoundException::new);

        magazzino.aggiornaCon(payload);

        magazzinoRepository.save(magazzino);
    }

    public void cancella(Long magazzinoId) {
        magazzinoRepository.deleteById(magazzinoId);
    }
}
