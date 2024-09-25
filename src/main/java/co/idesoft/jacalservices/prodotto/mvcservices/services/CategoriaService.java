package co.idesoft.jacalservices.prodotto.mvcservices.services;

import org.springframework.stereotype.Service;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCategoriaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Categoria;
import co.idesoft.jacalservices.prodotto.mvcservices.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Long save(@Valid CreareCategoriaDto payload) {
        return categoriaRepository.save(Categoria.from(payload)).getCategoriaId();
    }
}
