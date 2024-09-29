package co.idesoft.architetture.mvcservices.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareDipendenteDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareDipendenteDto;
import co.idesoft.architetture.mvcservices.entities.Dipendente;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.DipendenteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public Long save(@Valid CreareDipendenteDto payload) {
        return dipendenteRepository.save(Dipendente.from(payload)).getDipendenteId();
    }

    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findDettaglio(Long dipendentiId) throws RecordNotFoundException {
        return dipendenteRepository.findById(dipendentiId)
                .orElseThrow(RecordNotFoundException::new);
    }

    public void update(Long dipendentiId, @Valid AggiornareDipendenteDto payload) throws RecordNotFoundException {
        Dipendente dipendente = dipendenteRepository.findById(dipendentiId)
                .orElseThrow(RecordNotFoundException::new);

        dipendente.aggiornaCon(payload);

        dipendenteRepository.save(dipendente);
    }

    public void cancella(Long dipendentiId) {
        dipendenteRepository.deleteById(dipendentiId);
    }
}
