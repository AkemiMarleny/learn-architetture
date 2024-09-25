package co.idesoft.jacalservices.prodotto.mvcservices.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCassaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Cassa;
import co.idesoft.jacalservices.prodotto.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.jacalservices.prodotto.mvcservices.repositories.CassaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class CassaService {

    private final CassaRepository cassaRepository;

    public Long save(@Valid CreareCassaDto payload) {
        return cassaRepository.save(Cassa.from(payload)).getCassaId();
    }

    public Page<Cassa> findAll(Pageable pageable) {
        return cassaRepository.findAll(pageable);
    }

    public Cassa findDettaglio(Long cassaId) throws RecordNotFoundException {
        return cassaRepository.findById(cassaId).orElseThrow(RecordNotFoundException::new);
    }
}
