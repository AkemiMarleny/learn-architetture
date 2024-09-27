package co.idesoft.jacalservices.prodotto.mvcservices.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareSupermercatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Supermercato;
import co.idesoft.jacalservices.prodotto.mvcservices.repositories.SupermercatoRepository;
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

}
