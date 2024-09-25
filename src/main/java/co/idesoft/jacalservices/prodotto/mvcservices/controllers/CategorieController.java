package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CategoriaCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CategoriaItemDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCategoriaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/categorie")
@RequiredArgsConstructor
@Slf4j
public class CategorieController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaCreatoDto> creareCategoria(@RequestBody CreareCategoriaDto request) {
        log.info("creando una nuova categoria con request: {}, request");
        Long categoriaId = categoriaService.save(request);

        return new ResponseEntity<>(new CategoriaCreatoDto(categoriaId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaItemDto>> getAllCategorie(Pageable pageable) {
        Page<CategoriaItemDto> categoriePage = categoriaService.findAll(pageable)
                .map(CategoriaItemDto::fromEntity);
        return ResponseEntity.ok(categoriePage);
    }
}
