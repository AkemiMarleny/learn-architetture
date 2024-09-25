package co.idesoft.jacalservices.prodotto.mvcservices.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.AggiornareCategoriaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCategoriaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Categoria;
import co.idesoft.jacalservices.prodotto.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.jacalservices.prodotto.mvcservices.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Long save(@Valid CreareCategoriaDto payload) {
        return categoriaRepository.save(Categoria.from(payload)).getCategoriaId();
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public Categoria findDettaglio(Long categoriaId) throws RecordNotFoundException {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(RecordNotFoundException::new);
    }

    public void update(Long categoriaId, @Valid AggiornareCategoriaDto payload) throws RecordNotFoundException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(RecordNotFoundException::new);

        categoria.aggiornaCon(payload);

        categoriaRepository.save(categoria);
    }
}
