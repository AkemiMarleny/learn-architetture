package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

import java.util.Optional;

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

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.AggiornareMagazzinoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareMagazzinoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.MagazzinoCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.MagazzinoDettaglioDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.MagazzinoItemDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Magazzino;
import co.idesoft.jacalservices.prodotto.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.jacalservices.prodotto.mvcservices.services.MagazzinoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/magazzini")
@RequiredArgsConstructor
@Slf4j
public class MagazzinoController {

    private final MagazzinoService magazzinoService;

    @GetMapping
    public ResponseEntity<Page<MagazzinoItemDto>> getAllMagazzini(Pageable pageable) {
        Page<MagazzinoItemDto> magazziniPage = magazzinoService.findAll(pageable)
                .map(MagazzinoItemDto::fromEntity);
        return ResponseEntity.ok(magazziniPage);
    }

    @PostMapping
    public ResponseEntity<MagazzinoCreatoDto> creareMagazzino(@RequestBody CreareMagazzinoDto request) {
        log.info("creando un nuovo magazzino con request: {}", request);
        Long magazzinoId = magazzinoService.save(request);

        return new ResponseEntity<>(new MagazzinoCreatoDto(magazzinoId), HttpStatus.CREATED);
    }

    @GetMapping("{magazzinoId}")
    public ResponseEntity<MagazzinoDettaglioDto> getDettaglioMagazzino(@PathVariable Long magazzinoId) {
        Optional<Magazzino> magazzino = magazzinoService.findDettaglio(magazzinoId);
        if (magazzino.isPresent()) {
            MagazzinoDettaglioDto magazzinoDettaglioDto = MagazzinoDettaglioDto.fromEntity(magazzino.get());

            return new ResponseEntity<>(magazzinoDettaglioDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{magazzinoId}")
    public ResponseEntity<Void> updateMagazzino(@PathVariable Long magazzinoId,
            @RequestBody AggiornareMagazzinoDto request) {
        try {
            magazzinoService.update(magazzinoId, request);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{magazzinoId}")
    public ResponseEntity<Void> cancellaMagazzino(@PathVariable Long magazzinoId) {
        magazzinoService.cancella(magazzinoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
