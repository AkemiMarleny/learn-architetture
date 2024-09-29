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

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareCassaDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CassaCreatoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CassaDettaglioDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CassaItemDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareCassaDto;
import co.idesoft.architetture.mvcservices.entities.Cassa;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.services.CassaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/casse")
@RequiredArgsConstructor
@Slf4j
public class CassaController {

    private final CassaService cassaService;

    @PostMapping
    public ResponseEntity<CassaCreatoDto> creareCassa(@RequestBody CreareCassaDto request) {
        log.info("creando una nuova cassa con request: {}, request");
        Long cassaId = cassaService.save(request);
        return new ResponseEntity<>(new CassaCreatoDto(cassaId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CassaItemDto>> getAllCasse(Pageable pageable) {
        Page<CassaItemDto> cassePage = cassaService.findAll(pageable)
                .map(CassaItemDto::fromEntity);
        return ResponseEntity.ok(cassePage);
    }

    @GetMapping("{cassaId}")
    public ResponseEntity<CassaDettaglioDto> getDettaglioCassa(@PathVariable Long cassaId) {

        try {
            Cassa cassaEnt = cassaService.findDettaglio(cassaId);
            CassaDettaglioDto cassaDettaglioDto = CassaDettaglioDto.fromEntity(cassaEnt);
            return new ResponseEntity<>(cassaDettaglioDto, HttpStatus.OK);
        } catch (RecordNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{cassaId}")
    public ResponseEntity<Void> updateCassa(@PathVariable Long cassaId,
            @RequestBody AggiornareCassaDto request) {
        try {
            cassaService.update(cassaId, request);
        } catch (RecordNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{cassaId}")
    public ResponseEntity<Void> cancellaCassa(@PathVariable Long cassaId) {
        cassaService.cancella(cassaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
