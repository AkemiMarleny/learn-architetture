package co.idesoft.jacalservices.prodotto.mvcservices.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareMagazzinoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Magazzino;
import co.idesoft.jacalservices.prodotto.mvcservices.repositories.MagazzinoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MagazzinoService {

    private final MagazzinoRepository magazzinoRepository;

    public Page<Magazzino> findAll(Pageable pageable) {
        return magazzinoRepository.findAll(pageable);
    }

    public Long save(@Valid CreareMagazzinoDto payload) {
        return magazzinoRepository.save(Magazzino.from(payload)).getMagazzinoId();
    }

    public void update(Long magazzinoId, @Valid AggiornareMagazzinoDto payload) {
        Optional<Magazzino> magazzino = magazzinoRepository.findById(magazzinoId);
        if (magazzino.isPresent()) {
            Magazzino magazzinoAModificare = magazzino.get();
            magazzinoAModificare.aggiornaCon(payload);

            magazzinoRepository.save(magazzinoAModificare);
        }
    }
}
