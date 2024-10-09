package co.idesoft.architetture.mvcservices.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCategoriaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CategoriaCreatoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CategoriaDettaglioDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CategoriaItemDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCategoriaDto;
import co.idesoft.architetture.mvcservices.entities.Categoria;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.services.CategoriaService;
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

        try {
            Long categoriaId = categoriaService.save(request);
            return new ResponseEntity<>(new CategoriaCreatoDto(categoriaId), HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaItemDto>> getAllCategorie(Pageable pageable, String q) {
        Page<CategoriaItemDto> categoriePage = categoriaService.findAll(pageable, q)
                .map(CategoriaItemDto::fromEntity);
        return ResponseEntity.ok(categoriePage);
    }

    @GetMapping("{categoriaId}")
    public ResponseEntity<CategoriaDettaglioDto> getDettaglioCategoria(@PathVariable Long categoriaId) {

        try {
            Categoria categoriaEnt = categoriaService.findDettaglio(categoriaId);
            CategoriaDettaglioDto categoriaDettaglioDto = CategoriaDettaglioDto.fromEntity(categoriaEnt);
            return new ResponseEntity<>(categoriaDettaglioDto, HttpStatus.OK);
        } catch (RecordNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{categoriaId}")
    public ResponseEntity<Void> updateCategoria(@PathVariable Long categoriaId,
            @RequestBody AggiornareCategoriaDto request) {
        try {
            categoriaService.update(categoriaId, request);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{categoriaId}")
    public ResponseEntity<Void> cancellaCategoria(@PathVariable Long categoriaId) {
        categoriaService.cancella(categoriaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
