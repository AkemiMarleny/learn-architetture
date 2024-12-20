package co.idesoft.architetture.mvcservices.services;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCategoriaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCategoriaDto;
import co.idesoft.architetture.mvcservices.entities.Categoria;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Validated
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Long save(@Valid CreareCategoriaDto payload) throws ConflictException {
        Categoria categoria = Categoria.from(payload);

        Long categorieContatore = categoriaRepository.countByChecksum(categoria.getChecksum());

        if (categorieContatore > 0) {
            throw new ConflictException();
        }
        return categoriaRepository.save(categoria).getCategoriaId();
    }

    public Page<Categoria> findAll(Pageable pageable, String query) {
        return categoriaRepository.findByNomeContaining(pageable, query);
    }

    public Categoria findDettaglio(Long categoriaId) throws RecordNotFoundException {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(RecordNotFoundException::new);
    }

    public void update(Long categoriaId, @Valid AggiornareCategoriaDto payload)
            throws RecordNotFoundException, ConflictException {

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(RecordNotFoundException::new);

        categoria.aggiornaCon(payload);

        Long categorieContatore = categoriaRepository.countByChecksumAndCategoriaIdNotIn(categoria.getChecksum(),
                Arrays.asList(categoriaId));

        if (categorieContatore > 0) {
            throw new ConflictException();
        }

        categoriaRepository.save(categoria);
    }

    public void cancella(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }
}
