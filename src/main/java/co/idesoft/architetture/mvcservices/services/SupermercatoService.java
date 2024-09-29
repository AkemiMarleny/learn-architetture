package co.idesoft.architetture.mvcservices.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareSupermercatoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareSupermercatoDto;
import co.idesoft.architetture.mvcservices.entities.Supermercato;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.SupermercatoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class SupermercatoService {

    private final SupermercatoRepository supermercatoRepository;

    public Long save(@Valid CreareSupermercatoDto payload) {
        return supermercatoRepository.save(Supermercato.from(payload)).getSupermercatoId();
    }

    public Page<Supermercato> findAll(Pageable pageable) {
        return supermercatoRepository.findAll(pageable);
    }

    public Supermercato findDettaglio(Long supermercatoId) throws RecordNotFoundException {
        return supermercatoRepository.findById(supermercatoId)
                .orElseThrow(RecordNotFoundException::new);
    }

    public void update(Long supermercatoId, @Valid AggiornareSupermercatoDto payload) throws RecordNotFoundException {
        Supermercato supermercato = supermercatoRepository.findById(supermercatoId)
                .orElseThrow(RecordNotFoundException::new);

        supermercato.aggiornaCon(payload);

        supermercatoRepository.save(supermercato);
    }

    public void cancella(Long supermercatoId) {
        supermercatoRepository.deleteById(supermercatoId);
    }

}
