package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

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

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.AggiornareSupermercatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareSupermercatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.SupermercatoCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.SupermercatoDettaglioDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.SupermercatoItemDto;
import co.idesoft.jacalservices.prodotto.mvcservices.entities.Supermercato;
import co.idesoft.jacalservices.prodotto.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.jacalservices.prodotto.mvcservices.services.SupermercatoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/supermercati")
@RequiredArgsConstructor
@Slf4j
public class SupermercatoController {

    private final SupermercatoService supermercatoService;

    @PostMapping
    public ResponseEntity<SupermercatoCreatoDto> creareSupermercato(@RequestBody CreareSupermercatoDto request) {
        log.info("creando un nuovo supermercato con request: {}, request");
        Long supermercatoId = supermercatoService.save(request);
        return new ResponseEntity<>(new SupermercatoCreatoDto(supermercatoId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<SupermercatoItemDto>> getAllSupermercati(Pageable pageable) {
        Page<SupermercatoItemDto> supermercatiPage = supermercatoService.findAll(pageable)
                .map(SupermercatoItemDto::fromEntity);

        return ResponseEntity.ok(supermercatiPage);
    }

    @GetMapping("{supermercatoId}")
    public ResponseEntity<SupermercatoDettaglioDto> getDettaglioSupermercato(@PathVariable Long supermercatoId) {

        try {
            Supermercato supermercatoEnt = supermercatoService.findDettaglio(supermercatoId);
            SupermercatoDettaglioDto supermercatoDettaglioDto = SupermercatoDettaglioDto.fromEntity(supermercatoEnt);
            return new ResponseEntity<>(supermercatoDettaglioDto, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{supermercatoId}")
    public ResponseEntity<Void> updateSupermercato(@PathVariable Long supermercatoId,
            @RequestBody AggiornareSupermercatoDto request) {
        try {
            supermercatoService.update(supermercatoId, request);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{supermercatoId}")
    public ResponseEntity<Void> cancellaSupermercato(@PathVariable Long supermercatoId) {
        supermercatoService.cancella(supermercatoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
